<template>
    <div>
        <Card>
            <div slot="extra"></div>
            <LamboTable ref="table" dataUrl="/manage/lamboRule/list" :columns="tableColumn" :searchParams="tableSearchParams">
                <div slot="search">
                    <Input v-model="search" placeholder="搜索" style="width: 200px" />
                    <Button type="primary" icon="ios-search" @click="doSearch">查询</Button>
                    <Button type="ghost" icon="plus-round" @click="forInsert">新增</Button>
                </div>
            </LamboTable>
        </Card>
    </div>
</template>
<script>
    ;
    import util from '@/libs/util.js';
    //编辑按钮
    const editButton = (vm, h, currentRow, index) => {
        return h('Button', {
            props: {
                type: "primary",
                size: "small"
            },
            style: {
                margin: '0 5px'
            },
            on: {
                'click': function() {
                    vm.goUpdatePage(currentRow.id);
                }
            }
        }, '编辑');
    };

    //删除按钮
    const deleteButton = (vm, h, currentRow, index) => {
        return h('Button', {
            props: {
                type: "error",
                size: "small"
            },
            style: {
                margin: '0 5px'
            },
            on: {
                'click': () => {
                    vm.doDelete(currentRow.id);
                }
            }
        }, '删除');
    };
    export default {
        data:function(){
            return {
                search:"",
                tableSearchParams:{}
            }
        },
        computed: {
            tableColumn() {
                let columns = [];
                let self = this;
                                                            columns.push({
                            title: 'id',
                            key: 'id',
                            align:"center"
                        });
                                                                                columns.push({
                            title: 'com_id',
                            key: 'comId',
                            align:"center"
                        });
                                                                                columns.push({
                            title: 'rule_id',
                            key: 'ruleId',
                            align:"center"
                        });
                                                                                columns.push({
                            title: 'rule_value',
                            key: 'ruleValue',
                            align:"center"
                        });
                                                                                columns.push({
                            title: 'rule_desc',
                            key: 'ruleDesc',
                            align:"center"
                        });
                                                    columns.push({
                    title: '操作',
                    align: "center",
                    render: function(h, param) {
                        return h('div', [
                            editButton(self, h, param.row, param.index),
                            deleteButton(self, h, param.row, param.index)
                        ]);
                    }
                });
                return columns;
            }
        },
        methods:{
            doSearch:function(){
                this.tableSearchParams = {
                    search:this.search
                }
            },
            forInsert: function() {
                this.$router.push({
                    name: 'lamboRuleEdit'
                });
            },
            goUpdatePage: function(id) {
                this.$router.push({
                    name: 'lamboRuleEdit',
                    query: {
                        id:id
                    }
                });
            },
            doDelete: function(id) {
                var self = this;
                this.$Modal.confirm({
                    title: '提示',
                    content: '<p>确定要删除吗?</p>',
                    onOk:() => {
                        util.ajax.get("/manage/lamboRule/delete/"+ id).then(function(resp) {
                            self.$Message.success('删除成功');
                            self.doSearch();
                        })
                    }
                });
            }
        }
    };
</script>

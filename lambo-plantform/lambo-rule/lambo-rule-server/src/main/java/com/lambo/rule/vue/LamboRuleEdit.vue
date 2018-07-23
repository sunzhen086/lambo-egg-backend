<template>
    <div>
        <Card>
            <p slot="title">
                lambo_rule修改
            </p>
            <div slot="extra">
                <Button type="primary" @click="formSubmit">保存</Button>
                <a href="#" @click="pageGoBack">
                    <Icon type="android-arrow-back"></Icon> 返回</a>
            </div>
            <Form  ref="formData" :model="formData" :label-width="80" :rules="ruleValidate">
                                                                                                                                                                                                            <FormItem label="com_id" prop="comId">
                                <Input v-model="formData.comId" placeholder="请输入com_id"></Input>
                            </FormItem>
                                                                                                                                                                            <FormItem label="rule_id" prop="ruleId">
                                <Input v-model="formData.ruleId" placeholder="请输入rule_id"></Input>
                            </FormItem>
                                                                                                                                                                            <FormItem label="rule_value" prop="ruleValue">
                                <Input v-model="formData.ruleValue" placeholder="请输入rule_value"></Input>
                            </FormItem>
                                                                                                                                                                            <FormItem label="rule_desc" prop="ruleDesc">
                                <Input v-model="formData.ruleDesc" placeholder="请输入rule_desc"></Input>
                            </FormItem>
                                                                        </Form>
        </Card>
    </div>
</template>

<script>
        import util from '@/libs/util.js';
    export default {
        data () {
            return {
                formData: {
                                                                        id:"",
                                                                                                comId:"",
                                                                                                ruleId:"",
                                                                                                ruleValue:"",
                                                                                                ruleDesc:"",
                                                            },
                ruleValidate: {

                }
            };
        },
        computed: {
            id: function() {
                return this.$route.query.id;
            }
        },
        watch:{

        },
        methods: {
            pageGoBack(){
                this.$router.go(-1);
            },
            formSubmit(){
                var self = this;
                var params = {
                                                                        id:self.formData.id,
                                                                                                comId:self.formData.comId,
                                                                                                ruleId:self.formData.ruleId,
                                                                                                ruleValue:self.formData.ruleValue,
                                                                                                ruleDesc:self.formData.ruleDesc,
                                                            };
                if(self.id) {  //修改
                    util.ajax.post("/manage/lamboRule/update/" + self.id, params).then(function(resp) {
                        self.$Message.success('保存成功');
                        self.pageGoBack();
                    })
                } else { //新增
                    util.ajax.post("/manage/lamboRule/create", params).then(function(resp) {
                        self.$Message.success('新增成功');
                        self.pageGoBack();
                    })
                }
            },
            initData:function(){
                var self = this;
                //初始化数据
                if(self.id) { //修改
                    util.ajax.get("/manage/lamboRule/get/" + self.id).then(function(resp) {
                        var result = resp.data;
                                                                                    self.formData.id = result.id;
                                                                                                                self.formData.comId = result.comId;
                                                                                                                self.formData.ruleId = result.ruleId;
                                                                                                                self.formData.ruleValue = result.ruleValue;
                                                                                                                self.formData.ruleDesc = result.ruleDesc;
                                                                        });
                }
            }
        },
        mounted(){
            this.initData();
        }

    };
</script>

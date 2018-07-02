package com.lambo.dict.dao.model;

import java.io.Serializable;

public class LamboDict implements Serializable {
    private Integer id;

    /**
     * 字典名称
     *
     * @mbg.generated
     */
    private String dictName;

    /**
     * 字典类型(0:data,1:SQL)
     *
     * @mbg.generated
     */
    private String dictType;

    /**
     * 字典类型
     *
     * @mbg.generated
     */
    private String dictId;

    /**
     * 字典码
     *
     * @mbg.generated
     */
    private String dictKey;

    /**
     * 字典值
     *
     * @mbg.generated
     */
    private String dictValue;

    /**
     * 排序
     *
     * @mbg.generated
     */
    private Integer orderNum;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String dictDesc;

    /**
     * 字典值
     *
     * @mbg.generated
     */
    private String dictSql;

    /**
     * 数据源
     *
     * @mbg.generated
     */
    private String dictDataSource;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    public String getDictSql() {
        return dictSql;
    }

    public void setDictSql(String dictSql) {
        this.dictSql = dictSql;
    }

    public String getDictDataSource() {
        return dictDataSource;
    }

    public void setDictDataSource(String dictDataSource) {
        this.dictDataSource = dictDataSource;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dictName=").append(dictName);
        sb.append(", dictType=").append(dictType);
        sb.append(", dictId=").append(dictId);
        sb.append(", dictKey=").append(dictKey);
        sb.append(", dictValue=").append(dictValue);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", dictDesc=").append(dictDesc);
        sb.append(", dictSql=").append(dictSql);
        sb.append(", dictDataSource=").append(dictDataSource);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LamboDict other = (LamboDict) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDictName() == null ? other.getDictName() == null : this.getDictName().equals(other.getDictName()))
            && (this.getDictType() == null ? other.getDictType() == null : this.getDictType().equals(other.getDictType()))
            && (this.getDictId() == null ? other.getDictId() == null : this.getDictId().equals(other.getDictId()))
            && (this.getDictKey() == null ? other.getDictKey() == null : this.getDictKey().equals(other.getDictKey()))
            && (this.getDictValue() == null ? other.getDictValue() == null : this.getDictValue().equals(other.getDictValue()))
            && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(other.getOrderNum()))
            && (this.getDictDesc() == null ? other.getDictDesc() == null : this.getDictDesc().equals(other.getDictDesc()))
            && (this.getDictSql() == null ? other.getDictSql() == null : this.getDictSql().equals(other.getDictSql()))
            && (this.getDictDataSource() == null ? other.getDictDataSource() == null : this.getDictDataSource().equals(other.getDictDataSource()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDictName() == null) ? 0 : getDictName().hashCode());
        result = prime * result + ((getDictType() == null) ? 0 : getDictType().hashCode());
        result = prime * result + ((getDictId() == null) ? 0 : getDictId().hashCode());
        result = prime * result + ((getDictKey() == null) ? 0 : getDictKey().hashCode());
        result = prime * result + ((getDictValue() == null) ? 0 : getDictValue().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getDictDesc() == null) ? 0 : getDictDesc().hashCode());
        result = prime * result + ((getDictSql() == null) ? 0 : getDictSql().hashCode());
        result = prime * result + ((getDictDataSource() == null) ? 0 : getDictDataSource().hashCode());
        return result;
    }
}
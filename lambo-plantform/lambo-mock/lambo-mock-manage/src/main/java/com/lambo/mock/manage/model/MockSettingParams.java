package com.lambo.mock.manage.model;

public class MockSettingParams {
    private String mockId;

    private String paramKey;

    private String paramType;

    private String necessary;

    private String note;

    private String groupKey;

    private Integer orderSeq;

    public String getMockId() {
        return mockId;
    }

    public void setMockId(String mockId) {
        this.mockId = mockId == null ? null : mockId.trim();
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey == null ? null : paramKey.trim();
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType == null ? null : paramType.trim();
    }

    public String getNecessary() {
        return necessary;
    }

    public void setNecessary(String necessary) {
        this.necessary = necessary == null ? null : necessary.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey == null ? null : groupKey.trim();
    }

    public Integer getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Integer orderSeq) {
        this.orderSeq = orderSeq;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mockId=").append(mockId);
        sb.append(", paramKey=").append(paramKey);
        sb.append(", paramType=").append(paramType);
        sb.append(", necessary=").append(necessary);
        sb.append(", note=").append(note);
        sb.append(", groupKey=").append(groupKey);
        sb.append(", orderSeq=").append(orderSeq);
        sb.append("]");
        return sb.toString();
    }
}
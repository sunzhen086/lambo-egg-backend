package com.lambo.mock.manage.model;

public class MockStru {
    private String struId;

    private String struName;

    private String struUrl;

    private String struType;

    private String mockId;

    private String parentId;

    private String isUse;

    private String devStatus;

    private Integer orderSeq;

    public String getStruId() {
        return struId;
    }

    public void setStruId(String struId) {
        this.struId = struId == null ? null : struId.trim();
    }

    public String getStruName() {
        return struName;
    }

    public void setStruName(String struName) {
        this.struName = struName == null ? null : struName.trim();
    }

    public String getStruUrl() {
        return struUrl;
    }

    public void setStruUrl(String struUrl) {
        this.struUrl = struUrl == null ? null : struUrl.trim();
    }

    public String getStruType() {
        return struType;
    }

    public void setStruType(String struType) {
        this.struType = struType == null ? null : struType.trim();
    }

    public String getMockId() {
        return mockId;
    }

    public void setMockId(String mockId) {
        this.mockId = mockId == null ? null : mockId.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse == null ? null : isUse.trim();
    }

    public String getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(String devStatus) {
        this.devStatus = devStatus == null ? null : devStatus.trim();
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
        sb.append(", struId=").append(struId);
        sb.append(", struName=").append(struName);
        sb.append(", struUrl=").append(struUrl);
        sb.append(", struType=").append(struType);
        sb.append(", mockId=").append(mockId);
        sb.append(", parentId=").append(parentId);
        sb.append(", isUse=").append(isUse);
        sb.append(", devStatus=").append(devStatus);
        sb.append(", orderSeq=").append(orderSeq);
        sb.append("]");
        return sb.toString();
    }
}
package com.lambo.mock.manage.model;

public class MockSetting {
    private String mockId;

    private String mockName;

    private String mockUrl;

    private String mockType;

    private String provider;

    private String user;

    private String authMethod;

    private Boolean isPaging;

    private String note;

    private String createTime;

    private String updateTime;

    private String createUser;

    private String mockData;

    private String paramsDes;

    public String getMockId() {
        return mockId;
    }

    public void setMockId(String mockId) {
        this.mockId = mockId == null ? null : mockId.trim();
    }

    public String getMockName() {
        return mockName;
    }

    public void setMockName(String mockName) {
        this.mockName = mockName == null ? null : mockName.trim();
    }

    public String getMockUrl() {
        return mockUrl;
    }

    public void setMockUrl(String mockUrl) {
        this.mockUrl = mockUrl == null ? null : mockUrl.trim();
    }

    public String getMockType() {
        return mockType;
    }

    public void setMockType(String mockType) {
        this.mockType = mockType == null ? null : mockType.trim();
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(String authMethod) {
        this.authMethod = authMethod == null ? null : authMethod.trim();
    }

    public Boolean getIsPaging() {
        return isPaging;
    }

    public void setIsPaging(Boolean isPaging) {
        this.isPaging = isPaging;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getMockData() {
        return mockData;
    }

    public void setMockData(String mockData) {
        this.mockData = mockData == null ? null : mockData.trim();
    }

    public String getParamsDes() {
        return paramsDes;
    }

    public void setParamsDes(String paramsDes) {
        this.paramsDes = paramsDes == null ? null : paramsDes.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mockId=").append(mockId);
        sb.append(", mockName=").append(mockName);
        sb.append(", mockUrl=").append(mockUrl);
        sb.append(", mockType=").append(mockType);
        sb.append(", provider=").append(provider);
        sb.append(", user=").append(user);
        sb.append(", authMethod=").append(authMethod);
        sb.append(", isPaging=").append(isPaging);
        sb.append(", note=").append(note);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", mockData=").append(mockData);
        sb.append(", paramsDes=").append(paramsDes);
        sb.append("]");
        return sb.toString();
    }
}
package com.lambo.mock.manage.model;

public class MockDevelop {
    private String mockId;

    private String status;

    private String designer;

    private String developer;

    private String note;

    public String getMockId() {
        return mockId;
    }

    public void setMockId(String mockId) {
        this.mockId = mockId == null ? null : mockId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer == null ? null : designer.trim();
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer == null ? null : developer.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mockId=").append(mockId);
        sb.append(", status=").append(status);
        sb.append(", designer=").append(designer);
        sb.append(", developer=").append(developer);
        sb.append(", note=").append(note);
        sb.append("]");
        return sb.toString();
    }
}
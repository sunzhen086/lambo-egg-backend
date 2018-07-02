package com.lambo.mq.client.model;

import java.util.Date;

public class MqCompensate {
    private Integer id;

    private String topic;

    private String tag;

    private String messageKeys;

    private String compensateStatus;

    private String cause;

    private Date createDate;

    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }


    public String getMessageKeys() {
        return messageKeys;
    }

    public void setMessageKeys(String messageKeys) {
        this.messageKeys = messageKeys;
    }

    public String getCompensateStatus() {
        return compensateStatus;
    }

    public void setCompensateStatus(String compensateStatus) {
        this.compensateStatus = compensateStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", topic=").append(topic);
        sb.append(", tag=").append(tag);
        sb.append(", messageKeys=").append(messageKeys);
        sb.append(", compensateStatus=").append(compensateStatus);
        sb.append(", cause=").append(cause);
        sb.append(", createDate=").append(createDate);
        sb.append(", message=").append(message);
        sb.append("]");
        return sb.toString();
    }
}
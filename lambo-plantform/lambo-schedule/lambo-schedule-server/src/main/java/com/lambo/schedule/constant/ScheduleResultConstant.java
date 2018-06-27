package com.lambo.schedule.constant;

/**
 * 定时器常量类
 * Created by zxc on 2018/6/26.
 */
public enum ScheduleResultConstant {
    INVALID_LENGTH(10001, "Invalid length"),
    FAILED(0, "failed"),
    SUCCESS(1, "success");

    public int code;

    public String message;

    ScheduleResultConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

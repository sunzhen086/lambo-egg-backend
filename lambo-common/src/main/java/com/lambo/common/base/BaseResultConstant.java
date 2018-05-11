package com.lambo.common.base;

/**
 * upms系统接口结果常量枚举类
 * Created by lambo on 2017/2/18.
 */
public enum BaseResultConstant {

    FAILED(0, "failed"),
    SUCCESS(1, "success"),;

    public int code;

    public String message;

    BaseResultConstant(int code, String message) {
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

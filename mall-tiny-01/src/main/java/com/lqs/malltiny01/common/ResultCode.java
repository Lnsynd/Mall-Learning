package com.lqs.malltiny01.common;

/**
 * 2023/5/30/030  16:42
 */
public enum ResultCode {
    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败"),
    VALIDATE_FAILED(404,"参数检验失败"),
    UNAUTHORIZED(401,"未登录或Token已过期"),
    FORBIDDEN(403,"没有权限");

    long code;
    String message;
    ResultCode(long code,String message){
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

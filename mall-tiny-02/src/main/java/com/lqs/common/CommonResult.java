package com.lqs.common;

import lombok.Data;

/**
 * Created by 刘千山 on 2023/5/31/031-20:13
 */
@Data
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    public CommonResult() {
    }

    public CommonResult(Long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 请求成功
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 请求成功
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data,String message){
        return new CommonResult<>(ResultCode.SUCCESS.getCode(),message, data);
    }

    /**
     * 请求失败
     */
    public static <T> CommonResult<T> failed(){
        return failed(ResultCode.FAILED);
    }

    /**
     * 请求失败
     */
    public static <T> CommonResult<T> failed(IErrorCode iErrorCode){
        return new CommonResult<>(iErrorCode.getCode(),iErrorCode.getMessage(),null);
    }


    /**
     * 请求失败
     */
    public static <T> CommonResult<T> failed(String message){
        return new CommonResult<>(ResultCode.FAILED.getCode(), message,null);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
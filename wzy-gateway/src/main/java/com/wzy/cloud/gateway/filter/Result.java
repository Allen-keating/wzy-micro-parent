package com.wzy.cloud.gateway.filter;

/**
 * 统一响应结果封装类
 */
public class Result {
    private Boolean flag;
    private String message;

    public Result(Boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public Boolean getFlag() { return flag; }
    public void setFlag(Boolean flag) { this.flag = flag; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
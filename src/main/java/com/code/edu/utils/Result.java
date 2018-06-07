package com.code.edu.utils;

public class Result<T> {

    private String title;
    private String message;
    private boolean success;
    private Long code;
    private T result;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public Long getCode() {
        return code;
    }

    public T getResult() {
        return result;
    }
}

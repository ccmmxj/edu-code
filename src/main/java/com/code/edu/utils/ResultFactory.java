package com.code.edu.utils;

public final class ResultFactory {
    private ResultFactory() {
        super();
    }
    public static <T> Result<T> newInstaceSuccessResult(String message,Long code,T result){
        return newInstaceDefaultResult(message,code,result,true);
    }
    public static <T> Result<T> newInstaceFailResult(String message,Long code,T result){
        return newInstaceDefaultResult(message,code,result,false);
    }

    public static <T> Result<T> newInstaceDefaultResult(String message,Long code,T result,boolean success){
        return newInstaceResult("提示",message,success,code,result);
    }

    public static <T> Result<T> newInstaceResult(String title,String message,boolean success,Long code,T result){
        Result res = new Result();
        res.setCode(code);
        res.setMessage(message);
        res.setResult(result);
        res.setSuccess(success);
        res.setTitle(title);
        return res;
    }
}

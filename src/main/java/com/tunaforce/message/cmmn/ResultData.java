package com.tunaforce.message.cmmn;

public class ResultData<T> {
    private boolean success;
    private T data;
    private String error;

    public ResultData(boolean success, T data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }
}
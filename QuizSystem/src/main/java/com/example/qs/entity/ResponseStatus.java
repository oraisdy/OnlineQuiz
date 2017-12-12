package com.example.qs.entity;

/**
 * Created by phoebegl on 2017/12/12.
 */
public enum ResponseStatus {
    Success(1), Error(2);

    private int code;

    private ResponseStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return Integer.toString(code);
    }
}

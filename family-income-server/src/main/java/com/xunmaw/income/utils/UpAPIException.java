package com.xunmaw.income.utils;

public class UpAPIException extends UpException {
    public int statusCode;

    public UpAPIException(int statusCode, String msg) {
        super("api error code:" + statusCode + "; msg:" + msg);
        this.statusCode = statusCode;
    }

}

package com.xunmaw.income.utils;

import org.springframework.stereotype.Component;

public class UpException extends Exception {
    public UpException(String msg) {
        super(msg);
    }
}

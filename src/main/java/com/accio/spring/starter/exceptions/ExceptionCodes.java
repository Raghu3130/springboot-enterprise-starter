package com.accio.spring.starter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

public enum ExceptionCodes {
    CUSTOMER_NOT_FOUND_EXCEPTION("CUSTOMER_NOT_FOUND_EXCEPTION", "1001"),
    CUSTOMER_INVALID_DATA_EXCEPTION("CUSTOMER_INVALID_DATA_EXCEPTION", "1002"),
    CUSTOMER_INVALID_EMAIL_EXCEPTION("CUSTOMER_INVALID_EMAIL_EXCEPTION", "1003");


    private final String code;
    private final String name;

    ExceptionCodes(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static ExceptionCodes valueOfException(String n) {
        for (ExceptionCodes e : values()) {
            if (e.name.equals(n)) {
                return e;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}

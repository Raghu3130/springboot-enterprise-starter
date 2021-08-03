package com.accio.spring.starter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCodes {
    CUSTOMER_NOT_FOUND_EXCEPTION("CUSTOMER_NOT_FOUND_EXCEPTION", "1001"),
    CUSTOMER_INVALID_DATA_EXCEPTION("CUSTOMER_INVALID_DATA_EXCEPTION", "1002"),
    INTERNAL_ERROR("INTERNAL_ERROR", "500"),
    BAD_REQUEST("BAD_REQUEST", "400"),
    NOT_FOUND("NOT_FOUND", "404"),
    UNAUTHORIZED("UNAUTHORIZED", "401")
    ;


    private final String code;
    private final String name;

    ErrorCodes(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static ErrorCodes valueOfException(String n) {
        for (ErrorCodes e : values()) {
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


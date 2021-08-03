package com.accio.spring.starter.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, String errorCode) {
        super(message, errorCode);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause, ErrorCodes.UNAUTHORIZED.getCode());
    }

    public UnauthorizedException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public UnauthorizedException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }

    public UnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}

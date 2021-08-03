package com.accio.spring.starter.exceptions;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message, ErrorCodes.NOT_FOUND.getCode());
    }

    public NotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause, ErrorCodes.NOT_FOUND.getCode());
    }

    public NotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public NotFoundException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }

}

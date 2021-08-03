package com.accio.spring.starter.exceptions;

public class BaseException extends Exception {
    private String errorCode;

    public BaseException(String message) {
        super(message);
        this.errorCode = "";
    }

    public BaseException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BaseException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}

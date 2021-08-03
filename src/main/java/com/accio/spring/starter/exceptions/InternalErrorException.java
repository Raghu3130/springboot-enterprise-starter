package com.accio.spring.starter.exceptions;

public class InternalErrorException extends BaseRuntimeException {

    public InternalErrorException(String message) {
        super(message);
    }

    public InternalErrorException(String message, String errorCode) {
        super(message, errorCode);
    }

    public InternalErrorException(String message, Throwable cause) {
        super(message, cause, ErrorCodes.INTERNAL_ERROR.getCode());
    }

    public InternalErrorException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public InternalErrorException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }

    public InternalErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}

package com.accio.spring.starter.exceptions;

public class BadRequestException extends BaseException {
    public BadRequestException(Class clazz, String... searchParamsMap) {
        super(BaseExceptionHelper.generateMessage(
                "Bad request",
                clazz.getSimpleName(),
                BaseExceptionHelper.toMap(String.class, String.class, searchParamsMap)
        ));
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, String errorCode) {
        super(message, errorCode);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause, ErrorCodes.BAD_REQUEST.getCode());
    }

    public BadRequestException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public BadRequestException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }

    public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }

}

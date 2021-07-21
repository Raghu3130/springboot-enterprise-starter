package com.accio.spring.starter.exceptions;

public class BadRequestException extends RuntimeException {
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
}

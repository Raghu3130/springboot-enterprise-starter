package com.accio.spring.starter.exceptions;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(Class clazz, String operation) {
        super(BaseExceptionHelper.generateSimpleMessage("Something went wrong in " + operation, clazz.getSimpleName()));
    }

    public InternalServerErrorException(String message) {
        super(message);
    }
}

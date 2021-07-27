package com.accio.spring.starter.exceptions.customer;

import com.accio.spring.starter.exceptions.BaseExceptionHelper;

public class CustomerInvalidDataException extends RuntimeException {
    public CustomerInvalidDataException(String property, String value) {
        super("Invalid data in field " + property + " value " + value);
    }
}

package com.accio.spring.starter.exceptions.customer;

import com.accio.spring.starter.exceptions.BaseExceptionHelper;

public class CustomerInvalidEmailException extends RuntimeException {
    public CustomerInvalidEmailException(String email) {
        super("Invalid Email " + email);
    }
}

package com.accio.spring.starter.exceptions.customer;

import com.accio.spring.starter.exceptions.BaseExceptionHelper;
import com.accio.spring.starter.exceptions.ExceptionCodes;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String id) {
        super("Could not find customer with id " + id);
    }

    public String getErrorCode() {
        return ExceptionCodes.CUSTOMER_NOT_FOUND_EXCEPTION.getCode();
    }

}

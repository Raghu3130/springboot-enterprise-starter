package com.accio.spring.starter.exceptions.customer;

public class CustomerInvalidDataException extends RuntimeException {
    public CustomerInvalidDataException(String property, String value) {
        super("Invalid customer data in property " + property + " value " + value);
    }
}

package com.accio.spring.starter.exceptions.customer;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String id) {
        super("Could not find customer " + id);
    }
}

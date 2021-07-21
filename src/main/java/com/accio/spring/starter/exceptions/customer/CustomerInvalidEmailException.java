package com.accio.spring.starter.exceptions.customer;

public class CustomerInvalidEmailException extends RuntimeException {
    public CustomerInvalidEmailException(String email) {
        super("Invalid email " + email);
    }
}

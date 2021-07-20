package com.accio.spring.starter.exceptions.internal;

public class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}

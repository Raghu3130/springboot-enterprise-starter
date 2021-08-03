package com.accio.spring.starter.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ValidationError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public ValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    public ValidationError(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }
}


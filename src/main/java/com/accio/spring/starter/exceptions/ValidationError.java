package com.accio.spring.starter.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ValidationError {

    /**
     * To hold object name in string.
     */
    private String object;

    /**
     * To hold field name in string.
     */
    private String field;

    /**
     * To hold rejectedValue.
     */
    private Object rejectedValue;

    /**
     * To hold error message in string.
     */
    private String message;

    /**
     * Constructor with object and message.
     *
     * @param objectName   String
     * @param errorMessage String
     */
    public ValidationError(
            final String objectName,
            final String errorMessage
    ) {
        this.object = objectName;
        this.message = errorMessage;
    }

    /**
     * Constructor with all properties.
     *
     * @param objectName   String
     * @param fieldName    String
     * @param invalidValue Object
     * @param errorMessage String
     */
    public ValidationError(
            final String objectName,
            final String fieldName,
            final Object invalidValue,
            final String errorMessage
    ) {
        this.object = objectName;
        this.field = fieldName;
        this.rejectedValue = invalidValue;
        this.message = errorMessage;
    }

    /**
     * To get object property.
     *
     * @return String
     */
    public String getObject() {
        return this.object;
    }

    /**
     * To get field property.
     *
     * @return String
     */
    public String getField() {
        return this.field;
    }

    /**
     * To get rejectedValue property.
     *
     * @return Object
     */
    public Object getRejectedValue() {
        return this.rejectedValue;
    }

    /**
     * To get message property.
     *
     * @return String
     */
    public String getMessage() {
        return this.message;
    }

}

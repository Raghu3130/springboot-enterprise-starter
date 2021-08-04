package com.accio.spring.starter.exceptions;

public class InternalErrorException extends BaseRuntimeException {

    /**
     * Constructor with only message param.
     *
     * @param message String
     */
    public InternalErrorException(final String message) {
        super(message);
    }

    /**
     * Constructor with  message and errorCode param.
     *
     * @param message   String
     * @param errorCode String
     */
    public InternalErrorException(
            final String message,
            final String errorCode
    ) {
        super(message, errorCode);
    }

    /**
     * Constructor with  message and cause param.
     *
     * @param message String
     * @param cause   Throwable
     */
    public InternalErrorException(
            final String message,
            final Throwable cause
    ) {
        super(message, cause, ErrorCodes.INTERNAL_ERROR.getCode());
    }

    /**
     * Constructor with  message, cause and errorCode param.
     *
     * @param message   String
     * @param cause     Throwable
     * @param errorCode String
     */
    public InternalErrorException(
            final String message,
            final Throwable cause,
            final String errorCode
    ) {
        super(message, cause, errorCode);
    }

    /**
     * Constructor with  cause and errorCode param.
     *
     * @param cause     Throwable
     * @param errorCode String
     */
    public InternalErrorException(
            final Throwable cause,
            final String errorCode
    ) {
        super(cause, errorCode);
    }

    /**
     * Constructor with  all properties.
     *
     * @param message            String
     * @param cause              Throwable
     * @param enableSuppression  boolean
     * @param writableStackTrace boolean
     * @param errorCode          String
     */
    public InternalErrorException(
            final String message,
            final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace,
            final String errorCode
    ) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }

}

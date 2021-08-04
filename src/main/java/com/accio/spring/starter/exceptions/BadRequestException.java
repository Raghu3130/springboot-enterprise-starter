package com.accio.spring.starter.exceptions;

public class BadRequestException extends BaseException {

    /**
     * The BadRequestException for internal use.
     *
     * @param clazz           AnyClass.class
     * @param searchParamsMap String
     */
    public BadRequestException(
            final Class<?> clazz,
            final String... searchParamsMap
    ) {
        super(BaseExceptionHelper.generateMessage(
                "Bad request",
                clazz.getSimpleName(),
                BaseExceptionHelper.toMap(
                        String.class,
                        String.class,
                        (Object) searchParamsMap
                )
        ));
    }

    /**
     * The BadRequestException constructor to create from String message.
     *
     * @param message String
     */
    public BadRequestException(final String message) {
        super(message);
    }

    /**
     * The BadRequestException constructor to create from String message and
     * String errorCode.
     *
     * @param message   String
     * @param errorCode String
     */
    public BadRequestException(final String message, final String errorCode) {
        super(message, errorCode);
    }

    /**
     * The BadRequestException constructor to create with message and cause.
     *
     * @param message String
     * @param cause   Throwable
     */
    public BadRequestException(final String message, final Throwable cause) {
        super(message, cause, ErrorCodes.BAD_REQUEST.getCode());
    }

    /**
     * The BadRequestException constructor to create with message, cause and
     * errorCode.
     *
     * @param message   String
     * @param cause     Throwable
     * @param errorCode String
     */

    public BadRequestException(
            final String message,
            final Throwable cause,
            final String errorCode
    ) {
        super(message, cause, errorCode);
    }

    /**
     * The BadRequestException constructor to create with cause and errorCode.
     *
     * @param cause     Throwable
     * @param errorCode String
     */
    public BadRequestException(final Throwable cause, final String errorCode) {
        super(cause, errorCode);
    }

    /**
     * The BadRequestException constructor with extra params.
     *
     * @param message            String
     * @param cause              Throwable
     * @param enableSuppression  boolean
     * @param writableStackTrace boolean
     * @param errorCode          String
     */
    public BadRequestException(
            final String message,
            final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace,
            final String errorCode
    ) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }

}

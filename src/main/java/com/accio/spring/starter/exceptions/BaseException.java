package com.accio.spring.starter.exceptions;

public class BaseException extends Exception {
    /**
     * errorCode to hold app level errors code.
     */
    private String errorCode;

    /**
     * BaseException constructor.
     *
     * @param message String
     */
    public BaseException(final String message) {
        super(message);
        this.errorCode = "";
    }

    /**
     * BaseException constructor.
     *
     * @param message String
     * @param errCode String
     */
    public BaseException(final String message, final String errCode) {
        super(message);
        this.errorCode = errCode;
    }

    /**
     * BaseException constructor.
     *
     * @param message String
     * @param cause   Throwable
     * @param errCode String
     */
    public BaseException(
            final String message,
            final Throwable cause,
            final String errCode
    ) {
        super(message, cause);
        this.errorCode = errCode;
    }

    /**
     * BaseException constructor.
     *
     * @param cause   Throwable
     * @param errCode String
     */
    public BaseException(final Throwable cause, final String errCode) {
        super(cause);
        this.errorCode = errCode;
    }

    /**
     * BaseException constructor.
     *
     * @param message            String
     * @param cause              Throwable
     * @param enableSuppression  boolean
     * @param writableStackTrace boolean
     * @param errCode            String
     */
    public BaseException(
            final String message,
            final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace,
            final String errCode
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errCode;
    }

    /**
     * The method returns property errorCode.
     *
     * @return String
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * The method will set property errorCode of String Type.
     *
     * @param errCode String
     */
    public void setErrorCode(final String errCode) {
        this.errorCode = errCode;
    }

}

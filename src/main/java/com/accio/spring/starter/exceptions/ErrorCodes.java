package com.accio.spring.starter.exceptions;


public enum ErrorCodes {

    /**
     * Customer not found exception.
     */
    CUSTOMER_NOT_FOUND_EXCEPTION(
            "CUSTOMER_NOT_FOUND_EXCEPTION",
            "1001"
    ),

    /**
     * Customer invalid data exception.
     * mostly will be thrown when some wrong value will be passed in any
     * property
     */
    CUSTOMER_INVALID_DATA_EXCEPTION(
            "CUSTOMER_INVALID_DATA_EXCEPTION",
            "1002"
    ),

    /**
     * This is App level exception for internal server error shorten to
     * InternalError.
     */
    INTERNAL_ERROR(
            "INTERNAL_ERROR",
            "500"
    ),

    /**
     * This is App level exception for bad request.
     */
    BAD_REQUEST(
            "BAD_REQUEST",
            "400"
    ),

    /**
     * This is App level exception for not found.
     */
    NOT_FOUND(
            "NOT_FOUND",
            "404"
    ),

    /**
     * This is App level exception for unauthorized.
     */
    UNAUTHORIZED(
            "UNAUTHORIZED",
            "401"
    );

    /**
     * To hold exception code.
     */
    private final String code;

    /**
     * To hold exception name.
     */
    private final String name;

    /**
     * ErrorCodes constructor.
     *
     * @param errorName String
     * @param errorCode String
     */
    ErrorCodes(final String errorName, final String errorCode) {
        this.name = errorName;
        this.code = errorCode;
    }

    /**
     * To get exception enum by name.
     *
     * @param errorName String
     * @return ErrorCodes
     */
    public static ErrorCodes valueOfException(final String errorName) {
        for (ErrorCodes e : values()) {
            if (e.name.equals(errorName)) {
                return e;
            }
        }
        return null;
    }

    /**
     * To get code of current enum.
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * To get name of current enum.
     * @return String
     */
    public String getName() {
        return name;
    }

}

package com.accio.spring.starter.responses;

import java.util.List;

public interface StandardResponseBuilder {

    /**
     * To create a success response object.
     *
     * @param payload    <T>
     * @param status     String
     * @param statusCode Integer
     * @param message    String
     * @param <T>        no need to pass this param
     * @return StandardResponse<T>
     */
    <T> StandardResponse<T> createSuccessResponse(
            T payload,
            String status,
            Integer statusCode,
            String message
    );

    /**
     * To create StandardResponse for an Error.
     *
     * @param status     String
     * @param statusCode Integer
     * @param message    String
     * @param errorCode  String
     * @return StandardResponse<StandardResponseError>
     */
    StandardResponse<StandardResponseError> createErrorResponse(
            String status,
            Integer statusCode,
            String message,
            String errorCode
    );

    /**
     * To create StandardResponse for an Error.
     *
     * @param status     String
     * @param statusCode Integer
     * @param message    String
     * @param errorCode  String
     * @param subErrors  List<Object>
     * @return StandardResponse<StandardResponseError>
     */
    StandardResponse<StandardResponseError> createErrorResponse(
            String status,
            Integer statusCode,
            String message,
            String errorCode,
            List<Object> subErrors
    );

}

package com.accio.spring.starter.responses;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandardResponseBuilderImpl implements StandardResponseBuilder {

    /**
     * To create a success response object.
     *
     * @param payload    <T>
     * @param status     String
     * @param statusCode Integer
     * @param message    String
     * @param <T> don't pass this param
     * @return StandardResponse<T>
     */
    @Override
    public <T> StandardResponse<T> createSuccessResponse(
            final T payload,
            final String status,
            final Integer statusCode,
            final String message
    ) {
        StandardResponse<T> standardResponse = new StandardResponse<>();
        standardResponse.setSuccess(true);
        standardResponse.setStatus(status);
        standardResponse.setStatusCode(statusCode);
        standardResponse.setMessage(message);
        standardResponse.setPayload(payload);
        return standardResponse;
    }

    /**
     * To create StandardResponse for an Error.
     *
     * @param status     String
     * @param statusCode Integer
     * @param message    String
     * @param errorCode  String
     * @return StandardResponse<StandardResponseError>
     */
    @Override
    public StandardResponse<StandardResponseError>
    createErrorResponse(
            final String status,
            final Integer statusCode,
            final String message,
            final String errorCode
    ) {
        return createError(status, statusCode, message, errorCode);
    }

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
    @Override
    public StandardResponse<StandardResponseError>
    createErrorResponse(
            final String status,
            final Integer statusCode,
            final String message,
            final String errorCode,
            final List<Object> subErrors
    ) {
        StandardResponse<StandardResponseError>
                standardResponse = createError(
                status,
                statusCode,
                message,
                errorCode
        );
        standardResponse.setSubErrors(subErrors);
        return standardResponse;
    }

    /**
     * To create Error object from all required fields.
     *
     * @param status Response status as String
     * @param statusCode Response status code as Integer
     * @param message Response message as String
     * @param errorCode The error code you want to pass
     * @return StandardResponse<StandardResponseError>
     */
    private StandardResponse<StandardResponseError>
    createError(
            final String status,
            final Integer statusCode,
            final String message,
            final String errorCode
    ) {
        StandardResponse<StandardResponseError>
                standardResponse = new StandardResponse<>();
        standardResponse.setSuccess(false);
        standardResponse.setStatus(status);
        standardResponse.setStatusCode(statusCode);
        standardResponse.setMessage(message);
        standardResponse.setErrorCode(errorCode);
        return standardResponse;
    }

}

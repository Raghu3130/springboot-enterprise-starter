package com.accio.spring.starter.responses;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandardResponseBuilderImpl implements StandardResponseBuilder {

    @Override
    public <T> StandardResponse<T> createSuccessResponse(T payload, String status, Integer statusCode, String message) {
        StandardResponse<T> standardResponse = new StandardResponse<>();
        standardResponse.setSuccess(true);
        standardResponse.setStatus(status);
        standardResponse.setStatusCode(statusCode);
        standardResponse.setMessage(message);
        standardResponse.setPayload(payload);
        return standardResponse;
    }

    @Override
    public StandardResponse<StandardResponseError> createErrorResponse(String status, Integer statusCode, String message, String errorCode) {
        return createError(status, statusCode, message, errorCode);
    }

    @Override
    public StandardResponse<StandardResponseError> createErrorResponse(String status, Integer statusCode, String message, String errorCode, List<Object> subErrors) {
        StandardResponse<StandardResponseError> standardResponse = createError(status, statusCode, message, errorCode);
        standardResponse.setSubErrors(subErrors);
        return standardResponse;
    }

    private StandardResponse<StandardResponseError> createError(String status, Integer statusCode, String message, String errorCode) {
        StandardResponse<StandardResponseError> standardResponse = new StandardResponse<>();
        standardResponse.setSuccess(false);
        standardResponse.setStatus(status);
        standardResponse.setStatusCode(statusCode);
        standardResponse.setMessage(message);
        standardResponse.setErrorCode(errorCode);
        return standardResponse;
    }

}

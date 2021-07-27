package com.accio.spring.starter.responses;

import java.util.List;

public interface StandardResponseBuilder {
    <T> StandardResponse<T> createSuccessResponse(T payload, String status, Integer statusCode, String message);

    StandardResponse<StandardResponseError> createErrorResponse(String status, Integer statusCode, String message, String errorCode);

    StandardResponse<StandardResponseError> createErrorResponse(String status, Integer statusCode, String message, String errorCode, List<Object> subErrors);
}

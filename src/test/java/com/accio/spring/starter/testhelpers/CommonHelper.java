package com.accio.spring.starter.testhelpers;

import com.accio.spring.starter.responses.StandardResponse;
import org.springframework.http.HttpStatus;

public class CommonHelper {

    public static <T> StandardResponse createSuccessResponse(T payload, HttpStatus httpStatus, String message) {
        StandardResponse<T> standardResponse = new StandardResponse<>();
        standardResponse.setStatus(httpStatus.getReasonPhrase());
        standardResponse.setStatusCode(httpStatus.value());
        standardResponse.setMessage(message);
        standardResponse.setPayload(payload);

        return standardResponse;
    }

}

package com.accio.spring.starter.responses;

import java.util.ArrayList;
import java.util.List;

public class StandardResponse<T> {

    /**
     * To know is response for success or error.
     * it will be set false if any error occurred and set response as error
     */
    private Boolean success;

    /**
     * To pass success or error message.
     */
    private String message;

    /**
     * To pass response status.
     */
    private String status;

    /**
     * To pass response statusCode.
     */
    private Integer statusCode;

    /**
     * To pass errorCode of internal error only in error response.
     */
    private String errorCode;

    /**
     * To pass a list of subErrors only in error response.
     */
    private List<Object> subErrors;

    /**
     * To pass a payload only in success response.
     */
    private T payload;

    /**
     * To get success property.
     *
     * @return Boolean
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * To set success property.
     *
     * @param isSuccess Boolean
     */
    public void setSuccess(final Boolean isSuccess) {
        this.success = isSuccess;
    }

    /**
     * To get message property.
     *
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * To set message property.
     *
     * @param responseMessage String
     */
    public void setMessage(final String responseMessage) {
        this.message = responseMessage;
    }

    /**
     * To get status property.
     *
     * @return String
     */
    public String getStatus() {
        return status;
    }

    /**
     * To set status property.
     *
     * @param responseStatus String
     */
    public void setStatus(final String responseStatus) {
        this.status = responseStatus;
    }

    /**
     * To get statusCode property.
     *
     * @return Integer
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * To set statusCode property.
     *
     * @param responseStatusCode Integer
     */
    public void setStatusCode(final Integer responseStatusCode) {
        this.statusCode = responseStatusCode;
    }

    /**
     * To get errorCode property.
     *
     * @return String
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * To set errorCode property.
     *
     * @param internalErrorCode String
     */
    public void setErrorCode(final String internalErrorCode) {
        this.errorCode = internalErrorCode;
    }

    /**
     * To get subErrors property.
     *
     * @return List<Object>
     */
    public List<Object> getSubErrors() {
        return subErrors;
    }

    /**
     * To set subErrors property.
     *
     * @param internalSubErrors List<Object>
     */
    public void setSubErrors(final List<Object> internalSubErrors) {
        this.subErrors = internalSubErrors;
    }

    /**
     * To get payload property.
     *
     * @return T
     */
    public T getPayload() {
        return payload;
    }

    /**
     * To set payload property.
     *
     * @param responsePayload T
     */
    public void setPayload(final T responsePayload) {
        this.payload = responsePayload;
    }

    /**
     * To add subError in subErrors property.
     *
     * @param error Object
     */
    public void addSubError(final Object error) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(error);
    }

}

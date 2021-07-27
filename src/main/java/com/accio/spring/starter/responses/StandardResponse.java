package com.accio.spring.starter.responses;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StandardResponse<T> {

    Boolean success;
    String message;
    String status;
    Integer statusCode;

    String errorCode;
    List<Object> subErrors;


    T payload;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<Object> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(List<Object> subErrors) {
        this.subErrors = subErrors;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}

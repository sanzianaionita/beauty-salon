package com.example.beautysalon.customExceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private String errorMessage;
    private HttpStatus errorStatus;
    private Integer errorCode;

    public CustomException(String errorMessage, HttpStatus errorStatus, Integer errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorStatus = errorStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(HttpStatus errorStatus) {
        this.errorStatus = errorStatus;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}

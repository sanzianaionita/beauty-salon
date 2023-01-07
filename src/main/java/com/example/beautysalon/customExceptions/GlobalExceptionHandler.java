package com.example.beautysalon.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {

        CustomExceptionObject customExceptionObject = new CustomExceptionObject(ex.getErrorMessage(), ex.getErrorStatus(), ex.getErrorCode());
        return new ResponseEntity<>(customExceptionObject, ex.getErrorStatus() != null ? ex.getErrorStatus() : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        CustomExceptionObject customExceptionObject = new CustomExceptionObject(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(customExceptionObject, customExceptionObject.getErrorStatus());
    }
}

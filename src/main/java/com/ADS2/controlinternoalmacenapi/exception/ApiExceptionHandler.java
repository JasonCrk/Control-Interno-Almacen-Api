package com.ADS2.controlinternoalmacenapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotAuthenticatedException.class)
    public ResponseEntity<ErrorResponse> handlerNotAuthenticatedException(
            NotAuthenticatedException ex,
            WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<ErrorResponse> handlerInvalidJwtException(
            InvalidJwtException ex,
            WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}

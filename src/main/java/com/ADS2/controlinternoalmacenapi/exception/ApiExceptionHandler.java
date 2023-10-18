package com.ADS2.controlinternoalmacenapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFoundException(
            NotFoundException ex,
            WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

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

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handlerMissingParameterException(
            MissingServletRequestParameterException ex,
            WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("El parámetro " + ex.getParameterName() + " es requerido");
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlerValidationException(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(FileUploadFailedException.class)
    public ResponseEntity<ErrorResponse> handlerFileUploadFailedException(
            FileUploadFailedException ex
    ) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

package com.ADS2.controlinternoalmacenapi.exception;

public class InvalidJwtException extends RuntimeException {
    public InvalidJwtException() {
        super("El JWT es invalido");
    }
}

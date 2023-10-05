package com.ADS2.controlinternoalmacenapi.exception;

public class NotAuthenticatedException extends RuntimeException {
    public NotAuthenticatedException() {
        super("No estas autenticado");
    }
}

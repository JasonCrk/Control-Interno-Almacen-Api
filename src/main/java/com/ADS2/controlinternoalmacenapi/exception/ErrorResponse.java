package com.ADS2.controlinternoalmacenapi.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private int statusCode;
    private String message;
}

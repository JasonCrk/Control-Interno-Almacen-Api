package com.ADS2.controlinternoalmacenapi.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private int statusCode;
    private String message;
}

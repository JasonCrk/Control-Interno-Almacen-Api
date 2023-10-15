package com.ADS2.controlinternoalmacenapi.exception;

public class FileUploadFailedException extends RuntimeException {
    public FileUploadFailedException() {
        super("Hubo un error al tratar de subir el documento");
    }
}

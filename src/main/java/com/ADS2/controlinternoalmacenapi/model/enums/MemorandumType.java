package com.ADS2.controlinternoalmacenapi.model.enums;

public enum MemorandumType {
    SOLICITUD_DESIGNACION("Solicitud designacion"),
    DESIGNACION("Designacion");

    private final String name;

    private MemorandumType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

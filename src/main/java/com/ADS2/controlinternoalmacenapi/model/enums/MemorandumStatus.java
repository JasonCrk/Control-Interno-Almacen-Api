package com.ADS2.controlinternoalmacenapi.model.enums;

public enum MemorandumStatus {
    PENDIENTE("Pendiente"),
    APROBADO("Aprobado"),
    NO_APROBADO("No aprobado");

    private final String name;

    private MemorandumStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

package com.ADS2.controlinternoalmacenapi.model.enums;

public enum Role {
    ADMIN("Administrador"),
    JEFE_UNIDAD_FINANZAS("Jefe Unidad de Finanzas"),
    ASISTENTE("Asistente"),
    JEFE_UNIDAD_LOGISTICA("Jefe Unidad de Logistica"),
    TECNICO_ADMINISTRATIVO_ALMACEN("Técnico Administrativo de Almacén"),
    TECNICO_ADMINISTRATIVO_LOGISTICA("Técnico Administrativo de Logística"),
    ANALISTA_FINANZAS("Analista Finanzas");

    public final String fullName;

    private Role(String fullName) {
        this.fullName = fullName;
    }
}

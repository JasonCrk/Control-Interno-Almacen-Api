package com.ADS2.controlinternoalmacenapi.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignarAnalistaRequest {

    @NotNull(message = "El ID del analista es requerido")
    @Min(value = 0, message = "Debe de ser un entero positivo")
    private Long analistaId;
}

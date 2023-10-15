package com.ADS2.controlinternoalmacenapi.request;

import com.ADS2.controlinternoalmacenapi.validator.docxFileValidator.ValidDocxFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearActaRequest {

    @NotBlank(message = "El titulo es requerido")
    private String title;

    @NotNull(message = "El documento es requerido")
    @ValidDocxFile
    private MultipartFile document;
}

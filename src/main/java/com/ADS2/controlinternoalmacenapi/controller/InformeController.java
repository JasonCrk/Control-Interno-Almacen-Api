package com.ADS2.controlinternoalmacenapi.controller;

import com.ADS2.controlinternoalmacenapi.request.CrearInformeRequest;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
import com.ADS2.controlinternoalmacenapi.service.informe.InformeService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/informes")
public class InformeController {

    @Autowired
    private InformeService informeService;

    @PostMapping(path = "/sustento-diferencias")
    public ResponseEntity<MessageResponse> crearInformeDeSustentoDeDiferencias(
            @Valid CrearInformeRequest request
    ) {
        return new ResponseEntity<>(
                this.informeService.crearInformeDeSustentoDeDiferencias(request),
                HttpStatus.CREATED
        );
    }
}

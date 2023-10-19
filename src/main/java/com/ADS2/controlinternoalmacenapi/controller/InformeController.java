package com.ADS2.controlinternoalmacenapi.controller;

import com.ADS2.controlinternoalmacenapi.request.CrearInformeRequest;
import com.ADS2.controlinternoalmacenapi.response.ListResponse;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
import com.ADS2.controlinternoalmacenapi.response.informe.InformeDetails;
import com.ADS2.controlinternoalmacenapi.response.informe.InformeResponse;
import com.ADS2.controlinternoalmacenapi.service.informe.InformeService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/informes")
public class InformeController {

    @Autowired
    private InformeService informeService;

    @GetMapping(path = "/sustento-diferencias")
    public ResponseEntity<ListResponse<InformeResponse>> listarInformesDeSustentoDeDiferencias() {
        return ResponseEntity.ok(this.informeService.listarInformesDeSustentoDeDiferencias());
    }

    @GetMapping(path = "/{informeId}/sustento-diferencias")
    public ResponseEntity<InformeDetails> obtenerInformeDeSustentoDeDiferencias(
            @PathVariable("informeId") Long informeId
    ) {
        return ResponseEntity.ok(this.informeService.obtenerInformeDeSustentoDeDiferencias(informeId));
    }

    @PostMapping(path = "/sustento-diferencias")
    public ResponseEntity<MessageResponse> crearInformeDeSustentoDeDiferencias(
            @Valid CrearInformeRequest request
    ) {
        return new ResponseEntity<>(
                this.informeService.crearInformeDeSustentoDeDiferencias(request),
                HttpStatus.CREATED
        );
    }

    @PostMapping(path = "/faltante")
    public ResponseEntity<MessageResponse> crearInformeDeFaltante(
            @Valid CrearInformeRequest request
    ) {
        return new ResponseEntity<>(
                this.informeService.crearInformeDeFaltante(request),
                HttpStatus.CREATED
        );
    }
}

package com.ADS2.controlinternoalmacenapi.controller;

import com.ADS2.controlinternoalmacenapi.request.CrearActaRequest;
import com.ADS2.controlinternoalmacenapi.response.ListResponse;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
import com.ADS2.controlinternoalmacenapi.response.acta.ActaDetails;
import com.ADS2.controlinternoalmacenapi.response.acta.ActaResponse;
import com.ADS2.controlinternoalmacenapi.service.acta.ActaService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/actas")
@Validated
public class ActaController {

    @Autowired
    private ActaService actaService;

    @GetMapping(path = "/inventario")
    public ResponseEntity<ListResponse<ActaResponse>> listarActasDeInventario() {
        return ResponseEntity.ok(this.actaService.listarActasDeInventario());
    }

    @GetMapping(path = "/inventario/{actaId}")
    public ResponseEntity<ActaDetails> obtenerActaDeInventario(
            @PathVariable("actaId") Long actaId
    ) {
        return ResponseEntity.ok(this.actaService.obtenerActaDeInventario(actaId));
    }

    @PostMapping(path = "/inventario")
    public ResponseEntity<MessageResponse> crearActaDeInventario(
            @Valid CrearActaRequest request
    ) {
        return new ResponseEntity<>(
                this.actaService.crearActaDeInventario(request),
                HttpStatus.CREATED
        );
    }

    @PostMapping(path = "/entrega-productos-sin-fines-lucro")
    public ResponseEntity<MessageResponse> crearActaDeEntregaDeProductosSinFinesDeLucro(
            @Valid CrearActaRequest request
    ) {
        return new ResponseEntity<>(
                this.actaService.crearActaDeEntregaDeProductosSinFinesDeLucro(request),
                HttpStatus.CREATED
        );
    }
}

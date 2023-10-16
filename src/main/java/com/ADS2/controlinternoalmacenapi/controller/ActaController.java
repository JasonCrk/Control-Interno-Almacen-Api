package com.ADS2.controlinternoalmacenapi.controller;

import com.ADS2.controlinternoalmacenapi.model.Usuario;
import com.ADS2.controlinternoalmacenapi.request.CrearActaRequest;
import com.ADS2.controlinternoalmacenapi.response.ListResponse;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
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

    @PostMapping(path = "/entrega-productos-sin-fines-lucro")
    public ResponseEntity<MessageResponse> crearActaDeEntregaDeProductosSinFinesDeLucro(
            @Valid CrearActaRequest request,
            @RequestAttribute("user") Usuario user
    ) {
        return new ResponseEntity<>(
                this.actaService.crearActaDeEntregaDeProductosSinFinesDeLucro(request, user),
                HttpStatus.CREATED
        );
    }
}

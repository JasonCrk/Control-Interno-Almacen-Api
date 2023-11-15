package com.ADS2.controlinternoalmacenapi.controller;

import com.ADS2.controlinternoalmacenapi.request.AsignarAnalistaRequest;
import com.ADS2.controlinternoalmacenapi.request.CrearMemorandumRequest;
import com.ADS2.controlinternoalmacenapi.request.EditarMemorandumRequest;
import com.ADS2.controlinternoalmacenapi.response.ListResponse;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
import com.ADS2.controlinternoalmacenapi.response.memorandum.MemorandumDetails;
import com.ADS2.controlinternoalmacenapi.response.memorandum.MemorandumResponse;
import com.ADS2.controlinternoalmacenapi.service.memorandum.MemorandumService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/memorandums")
public class MemorandumController {

    @Autowired
    private MemorandumService memorandumService;

    @GetMapping
    public ResponseEntity<ListResponse<MemorandumResponse>> buscarMemorandums(
            @RequestParam(name = "q", required = false, defaultValue = "") String searchQuery
    ) {
        return ResponseEntity.ok(this.memorandumService.buscarMemorandums(searchQuery));
    }

    @GetMapping(path = "/{memorandumId}")
    public ResponseEntity<MemorandumDetails> obtenerMemorandumDeSolicitudDeDesignacion(
            @PathVariable("memorandumId") Long memorandumId
    ) {
        return ResponseEntity.ok(this.memorandumService.obtenerMemorandum(memorandumId));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> crearMemorandumDeSolicitudDeDesignacion(
            @Valid CrearMemorandumRequest request
    ) {
        return new ResponseEntity<>(
                this.memorandumService.crearMemorandumDeSolicitudDeDesignacion(request),
                HttpStatus.CREATED
        );
    }

    @PostMapping(path = "/{memorandumId}/asignar-analista")
    public ResponseEntity<MessageResponse> asignarAnalistaAMemorandumDeSolicitudDeDesignacion(
            @PathVariable("memorandumId") Long memorandumId,
            @Valid @RequestBody AsignarAnalistaRequest request
    ) {
        return ResponseEntity.ok(this.memorandumService.asignarAnalistaAMemorandumDeSolicitudDeDesignacion(
                memorandumId,
                request
        ));
    }

    @PostMapping(path = "/{memorandumId}/solicitud-designacion/aprobar")
    public ResponseEntity<MessageResponse> aprobarMemorandumDeSolicitudDeDesignacion(
            @PathVariable("memorandumId") Long memorandumId
    ) {
        return ResponseEntity.ok(this.memorandumService.aprobarMemorandumDeSolicitudDeDesignacion(memorandumId));
    }

    @PostMapping(path = "/{memorandumId}/designacion/aprobar")
    public ResponseEntity<MessageResponse> aprobarMemorandumDeDesignacion(
            @PathVariable("memorandumId") Long memorandumId
    ) {
        return ResponseEntity.ok(this.memorandumService.aprobarMemorandumDeDesignacion(memorandumId));
    }

    @PutMapping(path = "/{memorandumId}")
    public ResponseEntity<MessageResponse> editarMemorandumDeSolicitudDeDesignacion(
            @PathVariable("memorandumId") Long memorandumId,
            @Valid EditarMemorandumRequest request
    ) {
        return ResponseEntity.ok(this.memorandumService.editarMemorandum(
                memorandumId,
                request
        ));
    }

    @DeleteMapping(path = "/{memorandumId}")
    public ResponseEntity<MessageResponse> eliminarMemorandumDeSolicitudDeDesignacion(
            @PathVariable("memorandumId") Long memorandumId
    ) {
        return ResponseEntity.ok(this.memorandumService.elimarMemorandum(memorandumId));
    }
}

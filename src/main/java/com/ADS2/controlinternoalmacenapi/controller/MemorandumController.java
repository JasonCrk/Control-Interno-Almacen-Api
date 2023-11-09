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

    @GetMapping(path = "/designacion")
    public ResponseEntity<ListResponse<MemorandumResponse>> listarMemorandumsDeDesignacion() {
        return ResponseEntity.ok(this.memorandumService.listarMemorandumsDeDesignacion());
    }

    @GetMapping(path = "/solicitud-designacion")
    public ResponseEntity<ListResponse<MemorandumResponse>> buscarMemorandumsDeSolicitudDeDesignacion(
            @RequestParam(name = "q", required = false, defaultValue = "") String searchQuery
    ) {
        return ResponseEntity.ok(this.memorandumService.buscarMemorandumsDeSolicitudDeDesignacion(searchQuery));
    }

    @GetMapping(path = "/solicitud-asignacion")
    public ResponseEntity<ListResponse<MemorandumResponse>> buscarMemorandumsDeSolicitudDeAsignacion(
            @RequestParam(name = "q", required = false, defaultValue = "") String searchQuery
    ) {
        return ResponseEntity.ok(this.memorandumService.buscarMemorandumsDeSolicitudDeAsignacion(searchQuery));
    }

    @GetMapping(path = "/designacion/{memorandumId}")
    public ResponseEntity<MemorandumDetails> obtenerMemorandumDeDesignacion(
            @PathVariable("memorandumId") Long memorandumId
    ) {
        return ResponseEntity.ok(this.memorandumService.obtenerMemorandumDeDesignacion(memorandumId));
    }

    @GetMapping(path = "/solicitud-designacion/{memorandumId}")
    public ResponseEntity<MemorandumDetails> obtenerMemorandumDeSolicitudDeDesignacion(
            @PathVariable("memorandumId") Long memorandumId
    ) {
        return ResponseEntity.ok(this.memorandumService.obtenerMemorandumDeSolicitudDeDesignacion(memorandumId));
    }

    @GetMapping(path = "/solicitud-asignacion/{memorandumId}")
    public ResponseEntity<MemorandumDetails> obtenerMemorandumDeSolicitudDeAsignacion(
            @PathVariable("memorandumId") Long memorandumId
    ) {
        return ResponseEntity.ok(this.memorandumService.obtenerMemorandumDeSolicitudDeAsignacion(memorandumId));
    }

    @PostMapping(path = "/designacion")
    public ResponseEntity<MessageResponse> crearMemorandumDeDesignacion(
            @Valid CrearMemorandumRequest request
    ) {
        return new ResponseEntity<>(
                this.memorandumService.crearMemorandumDeDesignacion(request),
                HttpStatus.CREATED
        );
    }

    @PostMapping(path = "/solicitud-designacion")
    public ResponseEntity<MessageResponse> crearMemorandumDeSolicitudDeDesignacion(
            @Valid CrearMemorandumRequest request
    ) {
        return new ResponseEntity<>(
                this.memorandumService.crearMemorandumDeSolicitudDeDesignacion(request),
                HttpStatus.CREATED
        );
    }

    @PostMapping(path = "/solicitud-designacion/{memorandumId}/asignar-analista")
    public ResponseEntity<MessageResponse> asignarAnalistaAMemorandumDeSolicitudDeDesignacion(
            @PathVariable("memorandumId") Long memorandumId,
            @Valid @RequestBody AsignarAnalistaRequest request
    ) {
        return ResponseEntity.ok(this.memorandumService.asignarAnalistaAMemorandumDeSolicitudDeDesignacion(
                memorandumId,
                request
        ));
    }

    @PutMapping(path = "/solicitud-designacion/{memorandumId}")
    public ResponseEntity<MessageResponse> editarMemorandumDeSolicitudDeDesignacion(
            @PathVariable("memorandumId") Long memorandumId,
            @Valid EditarMemorandumRequest request
    ) {
        return ResponseEntity.ok(this.memorandumService.editarMemorandumDeSolicitudDeDesignacion(
                memorandumId,
                request
        ));
    }

    @PutMapping(path = "/designacion/{memorandumId}")
    public ResponseEntity<MessageResponse> editarMemorandumDeDesignacion(
            @PathVariable("memorandumId") Long memorandumId,
            @Valid EditarMemorandumRequest request
    ) {
        return ResponseEntity.ok(this.memorandumService.editarMemorandumDeDesignacion(memorandumId, request));
    }

    @DeleteMapping(path = "/solicitud-designacion/{memorandumId}")
    public ResponseEntity<MessageResponse> eliminarMemorandumDeSolicitudDeDesignacion(
            @PathVariable("memorandumId") Long memorandumId
    ) {
        return ResponseEntity.ok(this.memorandumService.elimarMemorandumDeSolicitudDeDesignacion(memorandumId));
    }

    @DeleteMapping(path = "/designacion/{memorandumId}")
    public ResponseEntity<MessageResponse> eliminarMemorandumDeDesignacion(
            @PathVariable("memorandumId") Long memorandumId
    ) {
        return ResponseEntity.ok(this.memorandumService.elimarMemorandumDeDesignacion(memorandumId));
    }
}

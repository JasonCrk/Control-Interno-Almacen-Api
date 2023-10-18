package com.ADS2.controlinternoalmacenapi.controller;

import com.ADS2.controlinternoalmacenapi.request.AsignarAnalistaRequest;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
import com.ADS2.controlinternoalmacenapi.response.memorandum.MemorandumDetails;
import com.ADS2.controlinternoalmacenapi.service.memorandum.MemorandumService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/memorandums")
public class MemorandumController {

    @Autowired
    private MemorandumService memorandumService;

    @GetMapping(path = "/{memorandumId}")
    public ResponseEntity<MemorandumDetails> obtenerMemorandumDeDesignacion(
            @PathVariable("memorandumId") Long memorandumId
    ) {
        return ResponseEntity.ok(this.memorandumService.obtenerMemorandumDeDesignacion(memorandumId));
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
}

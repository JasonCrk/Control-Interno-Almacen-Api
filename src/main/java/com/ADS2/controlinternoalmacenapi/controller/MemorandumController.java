package com.ADS2.controlinternoalmacenapi.controller;

import com.ADS2.controlinternoalmacenapi.response.memorandum.MemorandumDetails;
import com.ADS2.controlinternoalmacenapi.service.memorandum.MemorandumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

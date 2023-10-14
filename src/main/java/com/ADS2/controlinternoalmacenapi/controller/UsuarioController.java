package com.ADS2.controlinternoalmacenapi.controller;

import com.ADS2.controlinternoalmacenapi.response.ListResponse;
import com.ADS2.controlinternoalmacenapi.response.usuario.UsuarioResponse;
import com.ADS2.controlinternoalmacenapi.service.usuario.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(path = "/analistas")
    public ResponseEntity<ListResponse<UsuarioResponse>> buscarAnalistas(
            @RequestParam(name = "q") String searchQuery
    ) {
        return new ResponseEntity<>(this.usuarioService.buscarAnalistas(searchQuery), HttpStatus.OK);
    }
}

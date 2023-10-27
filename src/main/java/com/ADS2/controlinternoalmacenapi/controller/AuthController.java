package com.ADS2.controlinternoalmacenapi.controller;

import com.ADS2.controlinternoalmacenapi.model.Usuario;
import com.ADS2.controlinternoalmacenapi.request.LoginRequest;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
import com.ADS2.controlinternoalmacenapi.response.auth.JwtResponse;
import com.ADS2.controlinternoalmacenapi.response.usuario.UsuarioAuth;
import com.ADS2.controlinternoalmacenapi.service.auth.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping(path = "/user")
    public ResponseEntity<UsuarioAuth> retrieveUserByToken(
            @RequestAttribute("user") Usuario user
    ) {
        return ResponseEntity.ok(this.authService.retrieveUserByToken(user));
    }

    @GetMapping(path = "/verify-token")
    public ResponseEntity<MessageResponse> verifyToken() {
        return ResponseEntity.ok(new MessageResponse("El token de acceso es valido"));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody LoginRequest loginData
    ) {
        return new ResponseEntity<>(this.authService.login(loginData), HttpStatus.OK);
    }

    @PostMapping(path = "/refresh-token")
    public ResponseEntity<JwtResponse> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return new ResponseEntity<>(this.authService.refreshToken(request, response), HttpStatus.OK);
    }
}

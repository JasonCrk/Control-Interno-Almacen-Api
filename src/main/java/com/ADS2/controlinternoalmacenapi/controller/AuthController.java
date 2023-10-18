package com.ADS2.controlinternoalmacenapi.controller;

import com.ADS2.controlinternoalmacenapi.request.LoginRequest;
import com.ADS2.controlinternoalmacenapi.response.auth.JwtResponse;
import com.ADS2.controlinternoalmacenapi.service.auth.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

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

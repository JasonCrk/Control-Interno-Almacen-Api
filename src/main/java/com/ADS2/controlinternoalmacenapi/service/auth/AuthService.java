package com.ADS2.controlinternoalmacenapi.service.auth;

import com.ADS2.controlinternoalmacenapi.request.LoginRequest;
import com.ADS2.controlinternoalmacenapi.response.auth.JwtResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    JwtResponse login(LoginRequest loginData);
    JwtResponse refreshToken(HttpServletRequest request, HttpServletResponse response);
}

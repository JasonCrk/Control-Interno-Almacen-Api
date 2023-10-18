package com.ADS2.controlinternoalmacenapi.service.auth;

import com.ADS2.controlinternoalmacenapi.request.LoginRequest;
import com.ADS2.controlinternoalmacenapi.exception.InvalidJwtException;
import com.ADS2.controlinternoalmacenapi.exception.NotAuthenticatedException;
import com.ADS2.controlinternoalmacenapi.model.Token;
import com.ADS2.controlinternoalmacenapi.model.enums.TokenType;
import com.ADS2.controlinternoalmacenapi.model.Usuario;
import com.ADS2.controlinternoalmacenapi.repository.TokenRepository;
import com.ADS2.controlinternoalmacenapi.repository.UsuarioRepository;
import com.ADS2.controlinternoalmacenapi.response.auth.JwtResponse;
import com.ADS2.controlinternoalmacenapi.security.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository userRepository;
    private final TokenRepository tokenRepository;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponse login(LoginRequest loginData) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginData.getEmail(),
                        loginData.getPassword()
                )
        );
        var user = userRepository.findByEmail(loginData.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return JwtResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .authHeader("Bearer")
                .build();
    }

    @Override
    public JwtResponse refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        final String refreshToken;
        final String userEmail;

        if (authHeader == null ||!authHeader.startsWith("Bearer "))
            throw new NotAuthenticatedException();

        refreshToken = authHeader.substring(7);

        userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail == null) throw new InvalidJwtException();

        var user = this.userRepository.findByEmail(userEmail)
                .orElseThrow();

        if (!jwtService.isTokenValid(refreshToken, user))
            throw new InvalidJwtException();

        var accessToken = jwtService.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .authHeader("Bearer")
                .build();
    }

    private void saveUserToken(Usuario user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Usuario user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());

        if (validUserTokens.isEmpty())
            return;

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }
}

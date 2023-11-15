package com.ADS2.controlinternoalmacenapi.security;

import com.ADS2.controlinternoalmacenapi.model.enums.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> {
                        auth.requestMatchers("/api/auth/login", "/api/auth/refresh-token").permitAll()
                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/api/usuarios/analistas"
                                ).hasAnyAuthority(Role.JEFE_UNIDAD_FINANZAS.name(), Role.ADMIN.name())
                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/api/memorandums/{memorandumId}/asignar-analista",
                                        "/api/memorandums/{memorandumId}/designacion/aprobar"
                                ).hasAnyAuthority(Role.JEFE_UNIDAD_FINANZAS.name(), Role.ADMIN.name())
                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/api/actas/inventario",
                                        "/api/actas/inventario/{actaId}",
                                        "/api/informes/sustento-diferencias/{informeId}",
                                        "/api/informes/sustento-diferencias"
                                ).hasAnyAuthority(Role.JEFE_UNIDAD_LOGISTICA.name(), Role.ADMIN.name())
                                .requestMatchers(HttpMethod.POST,
                                        "/api/informes/faltante",
                                        "/api/memorandums/{memorandumId}/solicitud-designacion/aprobar"
                                )
                                    .hasAnyAuthority(Role.JEFE_UNIDAD_LOGISTICA.name(), Role.ADMIN.name())
                                .requestMatchers(HttpMethod.POST, "/api/actas/inventario")
                                    .hasAnyAuthority(Role.ANALISTA_FINANZAS.name(), Role.ADMIN.name())
                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/api/actas/entrega-productos-sin-fines-lucro",
                                        "/api/informes/sustento-diferencias"
                                ).hasAnyAuthority(Role.TECNICO_ADMINISTRATIVO_ALMACEN.name(), Role.ADMIN.name())
                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/api/memorandums",
                                        "/api/memorandums/{memorandumId}"
                                )
                                .hasAnyAuthority(
                                        Role.JEFE_UNIDAD_FINANZAS.name(),
                                        Role.TECNICO_ADMINISTRATIVO_LOGISTICA.name(),
                                        Role.JEFE_UNIDAD_LOGISTICA.name(),
                                        Role.ASISTENTE.name(),
                                        Role.ADMIN.name()
                                )
                                .requestMatchers(HttpMethod.POST, "/api/memorandums")
                                .hasAnyAuthority(
                                        Role.TECNICO_ADMINISTRATIVO_LOGISTICA.name(),
                                        Role.ASISTENTE.name(),
                                        Role.ADMIN.name()
                                )
                                .requestMatchers(HttpMethod.PUT, "/api/memorandums/{memorandumId}")
                                .hasAnyAuthority(
                                        Role.TECNICO_ADMINISTRATIVO_LOGISTICA.name(),
                                        Role.ASISTENTE.name(),
                                        Role.ADMIN.name()
                                )
                                .requestMatchers(HttpMethod.DELETE, "/api/memorandums/{memorandumId}")
                                .hasAnyAuthority(
                                        Role.TECNICO_ADMINISTRATIVO_LOGISTICA.name(),
                                        Role.ASISTENTE.name(),
                                        Role.ADMIN.name()
                                )
                                .anyRequest().authenticated();
                    }
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
                .build();
    }
}

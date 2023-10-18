package com.ADS2.controlinternoalmacenapi.security;

import com.ADS2.controlinternoalmacenapi.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/api/auth/**"
                        ).permitAll()
                        .requestMatchers(
                                "/api/usuarios/analistas",
                                "/api/memorandums/{memorandumId}",
                                "/api/memorandums/{memorandumId}/asignar-analista"
                        ).hasAnyAuthority(Role.JEFE_UNIDAD_FINANZAS.name(), Role.ADMIN.name())
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/actas/inventario"
                        ).hasAnyAuthority(Role.JEFE_UNIDAD_LOGISTICA.name(), Role.ADMIN.name())
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/actas/inventario"
                        ).hasAnyAuthority(Role.ANALISTA_FINANZAS.name(), Role.ADMIN.name())
                        .requestMatchers(
                                "/api/actas/entrega-productos-sin-fines-lucro"
                        ).hasAnyAuthority(Role.TECNICO_ADMINISTRATIVO_ALMACEN.name(), Role.ADMIN.name())
                        .anyRequest().hasAnyAuthority(Role.ADMIN.name())
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
                .build();
    }
}

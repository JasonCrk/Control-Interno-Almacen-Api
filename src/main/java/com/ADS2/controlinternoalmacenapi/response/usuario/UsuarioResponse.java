package com.ADS2.controlinternoalmacenapi.response.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    private Long id;
    private String avatar;
    private String firstName;
    private String lastName;
}

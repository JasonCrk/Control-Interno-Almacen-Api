package com.ADS2.controlinternoalmacenapi.response.memorandum;

import com.ADS2.controlinternoalmacenapi.response.usuario.UsuarioResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemorandumResponse {
    private Long id;
    private String title;
    private String createdAt;
    private UsuarioResponse assigned;
    private String status;
    private String type;
}

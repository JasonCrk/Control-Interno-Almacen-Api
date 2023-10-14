package com.ADS2.controlinternoalmacenapi.service.usuario;

import com.ADS2.controlinternoalmacenapi.response.ListResponse;
import com.ADS2.controlinternoalmacenapi.response.usuario.UsuarioResponse;

public interface UsuarioService {
    ListResponse<UsuarioResponse> buscarAnalistas(String searchQuery);
}

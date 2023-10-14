package com.ADS2.controlinternoalmacenapi.service.usuario;

import com.ADS2.controlinternoalmacenapi.model.Usuario;
import com.ADS2.controlinternoalmacenapi.model.enums.Role;
import com.ADS2.controlinternoalmacenapi.repository.UsuarioRepository;
import com.ADS2.controlinternoalmacenapi.response.ListResponse;
import com.ADS2.controlinternoalmacenapi.response.usuario.UsuarioMapper;
import com.ADS2.controlinternoalmacenapi.response.usuario.UsuarioResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository userRepository;

    @Override
    public ListResponse<UsuarioResponse> buscarAnalistas(String searchQuery) {
        List<Usuario> filteredUsers = this.userRepository.search(Role.ANALISTA_FINANZAS, searchQuery);
        List<UsuarioResponse> mappedUsers = UsuarioMapper.INSTANCE.toList(filteredUsers);
        return new ListResponse<>(mappedUsers);
    }
}

package com.ADS2.controlinternoalmacenapi.response.usuario;

import com.ADS2.controlinternoalmacenapi.model.Usuario;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(source = "avatarUrl", target = "avatar")
    UsuarioResponse toResponse(Usuario user);

    @Mapping(source = "avatarUrl", target = "avatar")
    List<UsuarioResponse> toList(List<Usuario> users);
}

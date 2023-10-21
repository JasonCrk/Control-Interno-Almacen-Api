package com.ADS2.controlinternoalmacenapi.response.acta;

import com.ADS2.controlinternoalmacenapi.model.Acta;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActaMapper {

    ActaMapper INSTANCE = Mappers.getMapper(ActaMapper.class);

    @Mapping(expression = "java(acta.getCreatedAt().toString())", target = "createdAt")
    ActaResponse toResponse(Acta acta);

    @Mapping(expression = "java(acta.getCreatedAt().toString())", target = "createdAt")
    List<ActaResponse> toList(List<Acta> actas);

    @Mapping(expression = "java(acta.getCreatedAt().toString())", target = "createdAt")
    ActaDetails toDetailResponse(Acta acta);
}

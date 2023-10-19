package com.ADS2.controlinternoalmacenapi.response.informe;

import com.ADS2.controlinternoalmacenapi.model.Informe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InformeMapper {

    InformeMapper INSTANCE = Mappers.getMapper(InformeMapper.class);

    @Mapping(expression = "java(informe.getCreatedAt().toString())", target = "createdAt")
    InformeDetails toDetailResponse(Informe informe);
}

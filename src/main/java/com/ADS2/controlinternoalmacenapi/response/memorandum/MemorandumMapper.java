package com.ADS2.controlinternoalmacenapi.response.memorandum;

import com.ADS2.controlinternoalmacenapi.model.Memorandum;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemorandumMapper {

    MemorandumMapper INSTANCE = Mappers.getMapper(MemorandumMapper.class);

    @Mapping(expression = "java(memorandum.getStatus().getName())", target = "status")
    @Mapping(expression = "java(memorandum.getCreatedAt().toString())", target = "createdAt")
    MemorandumDetails toDetailResponse(Memorandum memorandum);

    @Mapping(expression = "java(memorandum.getCreatedAt().toString())", target = "createdAt")
    @Mapping(expression = "java(memorandum.getStatus().getName())", target = "status")
    MemorandumResponse toResponse(Memorandum memorandum);

    @Mapping(expression = "java(memorandum.getCreatedAt().toString())", target = "createdAt")
    @Mapping(expression = "java(memorandum.getStatus().getName())", target = "status")
    List<MemorandumResponse> toListResponse(List<Memorandum> memorandums);
}

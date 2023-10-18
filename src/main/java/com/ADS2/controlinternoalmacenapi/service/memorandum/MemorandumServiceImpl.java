package com.ADS2.controlinternoalmacenapi.service.memorandum;

import com.ADS2.controlinternoalmacenapi.exception.NotFoundException;
import com.ADS2.controlinternoalmacenapi.model.Memorandum;
import com.ADS2.controlinternoalmacenapi.model.enums.MemorandumType;
import com.ADS2.controlinternoalmacenapi.repository.MemorandumRepository;
import com.ADS2.controlinternoalmacenapi.response.memorandum.MemorandumDetails;
import com.ADS2.controlinternoalmacenapi.response.memorandum.MemorandumMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemorandumServiceImpl implements MemorandumService {

    private final MemorandumRepository memorandumRepository;

    @Override
    public MemorandumDetails obtenerMemorandumDeDesignacion(Long memorandumId) {
        Memorandum memorandum = this.memorandumRepository
                .findByIdAndType(memorandumId, MemorandumType.DESIGNACION)
                .orElseThrow(() -> new NotFoundException("El memorandum no existe"));
        return MemorandumMapper.INSTANCE.toDetailResponse(memorandum);
    }
}

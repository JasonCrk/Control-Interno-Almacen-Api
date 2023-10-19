package com.ADS2.controlinternoalmacenapi.service.memorandum;

import com.ADS2.controlinternoalmacenapi.request.AsignarAnalistaRequest;
import com.ADS2.controlinternoalmacenapi.request.EditarMemorandumRequest;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
import com.ADS2.controlinternoalmacenapi.response.memorandum.MemorandumDetails;

public interface MemorandumService {
    MemorandumDetails obtenerMemorandumDeDesignacion(Long memorandumId);
    MessageResponse asignarAnalistaAMemorandumDeSolicitudDeDesignacion(
            Long memorandumId,
            AsignarAnalistaRequest request
    );
    MessageResponse editarMemorandumDeSolicitudDeDesignacion(Long memorandumId, EditarMemorandumRequest request);
}

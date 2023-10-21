package com.ADS2.controlinternoalmacenapi.service.memorandum;

import com.ADS2.controlinternoalmacenapi.request.AsignarAnalistaRequest;
import com.ADS2.controlinternoalmacenapi.request.CrearMemorandumRequest;
import com.ADS2.controlinternoalmacenapi.request.EditarMemorandumRequest;
import com.ADS2.controlinternoalmacenapi.response.ListResponse;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
import com.ADS2.controlinternoalmacenapi.response.memorandum.MemorandumDetails;
import com.ADS2.controlinternoalmacenapi.response.memorandum.MemorandumResponse;

public interface MemorandumService {
    ListResponse<MemorandumResponse> listarMemorandumsDeSolicitudDeDesignacion();
    MemorandumDetails obtenerMemorandumDeDesignacion(Long memorandumId);
    MemorandumDetails obtenerMemorandumDeSolicitudDeAsignacion(Long memorandumId);
    MessageResponse crearMemorandumDeSolicitudDeDesignacion(CrearMemorandumRequest request);
    MessageResponse crearMemorandumDeDesignacion(CrearMemorandumRequest request);
    MessageResponse asignarAnalistaAMemorandumDeSolicitudDeDesignacion(
            Long memorandumId,
            AsignarAnalistaRequest request
    );
    MessageResponse editarMemorandumDeSolicitudDeDesignacion(Long memorandumId, EditarMemorandumRequest request);
    MessageResponse editarMemorandumDeDesignacion(Long memorandumId, EditarMemorandumRequest request);
    MessageResponse elimarMemorandumDeSolicitudDeDesignacion(Long memorandumId);
    MessageResponse elimarMemorandumDeDesignacion(Long memorandumId);
}

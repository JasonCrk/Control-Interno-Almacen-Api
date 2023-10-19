package com.ADS2.controlinternoalmacenapi.service.informe;

import com.ADS2.controlinternoalmacenapi.request.CrearInformeRequest;
import com.ADS2.controlinternoalmacenapi.response.ListResponse;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
import com.ADS2.controlinternoalmacenapi.response.informe.InformeDetails;
import com.ADS2.controlinternoalmacenapi.response.informe.InformeResponse;

public interface InformeService {
    ListResponse<InformeResponse> listarInformesDeSustentoDeDiferencias();
    InformeDetails obtenerInformeDeSustentoDeDiferencias(Long informeId);
    MessageResponse crearInformeDeSustentoDeDiferencias(CrearInformeRequest request);
    MessageResponse crearInformeDeFaltante(CrearInformeRequest request);
}

package com.ADS2.controlinternoalmacenapi.service.informe;

import com.ADS2.controlinternoalmacenapi.request.CrearInformeRequest;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
import com.ADS2.controlinternoalmacenapi.response.informe.InformeDetails;

public interface InformeService {
    InformeDetails obtenerInformeDeSustentoDeDiferencias(Long informeId);
    MessageResponse crearInformeDeSustentoDeDiferencias(CrearInformeRequest request);
    MessageResponse crearInformeDeFaltante(CrearInformeRequest request);
}

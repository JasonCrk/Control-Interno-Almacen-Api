package com.ADS2.controlinternoalmacenapi.service.informe;

import com.ADS2.controlinternoalmacenapi.request.CrearInformeRequest;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;

public interface InformeService {
    MessageResponse crearInformeDeSustentoDeDiferencias(CrearInformeRequest request);
    MessageResponse crearInformeDeFaltante(CrearInformeRequest request);
}

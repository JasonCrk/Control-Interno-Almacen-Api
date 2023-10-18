package com.ADS2.controlinternoalmacenapi.service.acta;

import com.ADS2.controlinternoalmacenapi.request.CrearActaRequest;
import com.ADS2.controlinternoalmacenapi.response.ListResponse;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
import com.ADS2.controlinternoalmacenapi.response.acta.ActaResponse;

public interface ActaService {
    ListResponse<ActaResponse> listarActasDeInventario();
    MessageResponse crearActaDeEntregaDeProductosSinFinesDeLucro(CrearActaRequest request);
    MessageResponse crearActaDeInventario(CrearActaRequest request);
}

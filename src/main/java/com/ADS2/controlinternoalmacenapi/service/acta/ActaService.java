package com.ADS2.controlinternoalmacenapi.service.acta;

import com.ADS2.controlinternoalmacenapi.model.Usuario;
import com.ADS2.controlinternoalmacenapi.request.CrearActaRequest;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;

public interface ActaService {
    MessageResponse crearActaDeEntregaDeProductosSinFinesDeLucro(CrearActaRequest request, Usuario user);
}

package com.ADS2.controlinternoalmacenapi.service.acta;

import com.ADS2.controlinternoalmacenapi.exception.FileUploadFailedException;
import com.ADS2.controlinternoalmacenapi.firebase.FirebaseStorageService;
import com.ADS2.controlinternoalmacenapi.model.Acta;
import com.ADS2.controlinternoalmacenapi.model.enums.ActaType;
import com.ADS2.controlinternoalmacenapi.repository.ActaRepository;
import com.ADS2.controlinternoalmacenapi.request.CrearActaRequest;
import com.ADS2.controlinternoalmacenapi.response.ListResponse;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
import com.ADS2.controlinternoalmacenapi.response.acta.ActaMapper;
import com.ADS2.controlinternoalmacenapi.response.acta.ActaResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActaServiceImpl implements ActaService {

    private final ActaRepository actaRepository;

    private final FirebaseStorageService storageService;

    @Override
    public ListResponse<ActaResponse> listarActasDeInventario() {
        List<Acta> actas = this.actaRepository.findByOrderByCreatedAtDesc();
        return new ListResponse<>(ActaMapper.INSTANCE.toList(actas));
    }

    @Override
    public MessageResponse crearActaDeEntregaDeProductosSinFinesDeLucro(CrearActaRequest request) {
        Acta acta = new Acta();
        acta.setType(ActaType.ENTREGA_PRODUCTOS_SIN_FINES_LUCRO);

        this.saveActa(acta, request);

        return new MessageResponse("El acta se ha guardado exitosamente");
    }

    @Override
    public MessageResponse crearActaDeInventario(CrearActaRequest request) {
        Acta acta = new Acta();
        acta.setType(ActaType.INVENTARIO);

        this.saveActa(acta, request);

        return new MessageResponse("El acta se ha guardado exitosamente");
    }

    private void saveActa(Acta acta, CrearActaRequest request) {
        acta.setTitle(request.getTitle());

        try {
            String uploadedDocumentUrl = this.storageService.uploadFile(request.getDocument());
            acta.setDocumentUrl(uploadedDocumentUrl);
        } catch (IOException e) {
            throw new FileUploadFailedException();
        }

        this.actaRepository.save(acta);
    }
}

package com.ADS2.controlinternoalmacenapi.service.acta;

import com.ADS2.controlinternoalmacenapi.exception.FileUploadFailedException;
import com.ADS2.controlinternoalmacenapi.firebase.FirebaseStorageService;
import com.ADS2.controlinternoalmacenapi.model.Acta;
import com.ADS2.controlinternoalmacenapi.model.Usuario;
import com.ADS2.controlinternoalmacenapi.model.enums.ActaType;
import com.ADS2.controlinternoalmacenapi.repository.ActaRepository;
import com.ADS2.controlinternoalmacenapi.request.CrearActaRequest;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ActaServiceImpl implements ActaService {

    private final ActaRepository actaRepository;

    private final FirebaseStorageService storageService;

    @Override
    public MessageResponse crearActaDeEntregaDeProductosSinFinesDeLucro(
            CrearActaRequest request,
            Usuario user
    ) {
        Acta acta = new Acta();
        acta.setUser(user);
        acta.setType(ActaType.ENTREGA_PRODUCTOS_SIN_FINES_LUCRO);

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

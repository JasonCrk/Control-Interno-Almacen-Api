package com.ADS2.controlinternoalmacenapi.service.informe;

import com.ADS2.controlinternoalmacenapi.exception.FileUploadFailedException;
import com.ADS2.controlinternoalmacenapi.exception.NotFoundException;
import com.ADS2.controlinternoalmacenapi.firebase.FirebaseStorageService;
import com.ADS2.controlinternoalmacenapi.model.Informe;
import com.ADS2.controlinternoalmacenapi.model.enums.InformeType;
import com.ADS2.controlinternoalmacenapi.repository.InformeRepository;
import com.ADS2.controlinternoalmacenapi.request.CrearInformeRequest;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
import com.ADS2.controlinternoalmacenapi.response.informe.InformeDetails;
import com.ADS2.controlinternoalmacenapi.response.informe.InformeMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class InformeServiceImpl implements InformeService {

    private final InformeRepository informeRepository;

    private final FirebaseStorageService storageService;

    @Override
    public InformeDetails obtenerInformeDeSustentoDeDiferencias(Long informeId) {
        Informe informe = this.informeRepository
                .findByIdAndType(informeId, InformeType.SUSTENTO_DIFERENCIAS)
                .orElseThrow(() -> new NotFoundException("El informe no existe"));
        return InformeMapper.INSTANCE.toDetailResponse(informe);
    }

    @Override
    public MessageResponse crearInformeDeSustentoDeDiferencias(CrearInformeRequest request) {
        Informe informe = new Informe();
        informe.setType(InformeType.SUSTENTO_DIFERENCIAS);

        this.saveInforme(informe, request);

        return new MessageResponse("El informe se ha guardado exitosamente");
    }

    @Override
    public MessageResponse crearInformeDeFaltante(CrearInformeRequest request) {
        Informe informe = new Informe();
        informe.setType(InformeType.FALTANTE);

        this.saveInforme(informe, request);

        return new MessageResponse("El informe se ha guardado exitosamente");
    }

    private void saveInforme(Informe informe, CrearInformeRequest request) {
        informe.setTitle(request.getTitle());

        try {
            String uploadedDocumentUrl = this.storageService.uploadFile(request.getDocument());
            informe.setDocumentUrl(uploadedDocumentUrl);
        } catch (IOException e) {
            throw new FileUploadFailedException();
        }

        this.informeRepository.save(informe);
    }
}

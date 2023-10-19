package com.ADS2.controlinternoalmacenapi.service.memorandum;

import com.ADS2.controlinternoalmacenapi.exception.FileUploadFailedException;
import com.ADS2.controlinternoalmacenapi.exception.NotFoundException;
import com.ADS2.controlinternoalmacenapi.exception.UnexpectedValueException;
import com.ADS2.controlinternoalmacenapi.firebase.FirebaseStorageService;
import com.ADS2.controlinternoalmacenapi.model.Memorandum;
import com.ADS2.controlinternoalmacenapi.model.Usuario;
import com.ADS2.controlinternoalmacenapi.model.enums.MemorandumType;
import com.ADS2.controlinternoalmacenapi.model.enums.Role;
import com.ADS2.controlinternoalmacenapi.repository.MemorandumRepository;
import com.ADS2.controlinternoalmacenapi.repository.UsuarioRepository;
import com.ADS2.controlinternoalmacenapi.request.AsignarAnalistaRequest;
import com.ADS2.controlinternoalmacenapi.request.EditarMemorandumRequest;
import com.ADS2.controlinternoalmacenapi.response.MessageResponse;
import com.ADS2.controlinternoalmacenapi.response.memorandum.MemorandumDetails;
import com.ADS2.controlinternoalmacenapi.response.memorandum.MemorandumMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MemorandumServiceImpl implements MemorandumService {

    private final MemorandumRepository memorandumRepository;
    private final UsuarioRepository userRepository;

    private final FirebaseStorageService storageService;

    @Override
    public MemorandumDetails obtenerMemorandumDeDesignacion(Long memorandumId) {
        Memorandum memorandum = this.memorandumRepository
                .findByIdAndType(memorandumId, MemorandumType.DESIGNACION)
                .orElseThrow(() -> new NotFoundException("El memorandum no existe"));
        return MemorandumMapper.INSTANCE.toDetailResponse(memorandum);
    }

    @Override
    public MessageResponse asignarAnalistaAMemorandumDeSolicitudDeDesignacion(
            Long memorandumId,
            AsignarAnalistaRequest request
    ) {
        Memorandum memorandum = this.memorandumRepository
                .findByIdAndType(memorandumId, MemorandumType.SOLICITUD_DESIGNACION)
                .orElseThrow(() -> new NotFoundException("El memorandum no existe"));

        if (memorandum.getAssigned() != null)
            throw new UnexpectedValueException("El memorandum ya tiene un analista asignado");

        Usuario analyst = this.userRepository
                .findByIdAndRole(request.getAnalistaId(), Role.ANALISTA_FINANZAS)
                .orElseThrow(() -> new NotFoundException("El analista no existe"));

        memorandum.setAssigned(analyst);
        this.memorandumRepository.save(memorandum);

        return new MessageResponse("Asignación exitosa");
    }

    @Override
    public MessageResponse editarMemorandumDeSolicitudDeDesignacion(Long memorandumId, EditarMemorandumRequest request) {
        Memorandum memorandum = this.memorandumRepository
                .findByIdAndType(memorandumId, MemorandumType.SOLICITUD_DESIGNACION)
                .orElseThrow(() -> new NotFoundException("El memorandum no existe"));

        memorandum.setTitle(request.getTitle());

        if (request.getDocument() != null) {
            try {
                String uploadedDocumentUrl = this.storageService.uploadFile(request.getDocument());
                memorandum.setDocumentUrl(uploadedDocumentUrl);
            } catch (IOException e) {
                throw new FileUploadFailedException();
            }
        }

        this.memorandumRepository.save(memorandum);

        return new MessageResponse("Se ha editado el memorandum exitosamente");
    }

    @Override
    public MessageResponse elimarMemorandumDeSolicitudDeDesignacion(Long memorandumId) {
        Memorandum memorandum = this.memorandumRepository
                .findByIdAndType(memorandumId, MemorandumType.SOLICITUD_DESIGNACION)
                .orElseThrow(() -> new NotFoundException("El memorandum no existe"));

        this.memorandumRepository.delete(memorandum);

        return new MessageResponse("El memorandum ha sido eliminado exitosamente");
    }
}

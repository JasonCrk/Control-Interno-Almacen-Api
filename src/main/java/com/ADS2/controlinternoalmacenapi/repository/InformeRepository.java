package com.ADS2.controlinternoalmacenapi.repository;

import com.ADS2.controlinternoalmacenapi.model.Informe;
import com.ADS2.controlinternoalmacenapi.model.enums.InformeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InformeRepository extends JpaRepository<Informe, Long> {
    Optional<Informe> findByIdAndType(Long id, InformeType type);
}

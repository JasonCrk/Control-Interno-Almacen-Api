package com.ADS2.controlinternoalmacenapi.repository;

import com.ADS2.controlinternoalmacenapi.model.InformeSustentoDiferencias;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformeSustentoDiferenciasRepository extends JpaRepository<InformeSustentoDiferencias, Long> {
}

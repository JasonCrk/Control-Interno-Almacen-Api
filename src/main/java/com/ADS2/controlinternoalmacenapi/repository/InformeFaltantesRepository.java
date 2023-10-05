package com.ADS2.controlinternoalmacenapi.repository;

import com.ADS2.controlinternoalmacenapi.model.InformeFaltantes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformeFaltantesRepository extends JpaRepository<InformeFaltantes, Long> {
}

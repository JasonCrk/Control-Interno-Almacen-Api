package com.ADS2.controlinternoalmacenapi.repository;

import com.ADS2.controlinternoalmacenapi.model.Acta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActaRepository extends JpaRepository<Acta, Long> {
    List<Acta> findByOrderByCreatedAtDesc();
}

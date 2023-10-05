package com.ADS2.controlinternoalmacenapi.repository;

import com.ADS2.controlinternoalmacenapi.model.Memorandum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemorandumRepository extends JpaRepository<Memorandum, Long> {
}

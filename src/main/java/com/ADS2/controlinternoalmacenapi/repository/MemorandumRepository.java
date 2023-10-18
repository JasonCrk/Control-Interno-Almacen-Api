package com.ADS2.controlinternoalmacenapi.repository;

import com.ADS2.controlinternoalmacenapi.model.Memorandum;
import com.ADS2.controlinternoalmacenapi.model.enums.MemorandumType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemorandumRepository extends JpaRepository<Memorandum, Long> {
    Optional<Memorandum> findByIdAndType(Long id, MemorandumType type);
}

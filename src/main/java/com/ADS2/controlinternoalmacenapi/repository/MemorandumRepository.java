package com.ADS2.controlinternoalmacenapi.repository;

import com.ADS2.controlinternoalmacenapi.model.Memorandum;
import com.ADS2.controlinternoalmacenapi.model.enums.MemorandumType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemorandumRepository extends JpaRepository<Memorandum, Long> {
    List<Memorandum> findByTypeOrderByCreatedAtDesc(MemorandumType type);

    @Query(value = """
            SELECT m FROM Memorandum m WHERE\s
            UPPER(m.title) LIKE UPPER(:title||'%')\s
            ORDER BY m.createdAt DESC\s
            """)
    List<Memorandum> searchByTitleStartingWithOrderByCreatedAtDesc(
            @Param("title") String title
    );

    Optional<Memorandum> findByIdAndType(Long id, MemorandumType type);
}

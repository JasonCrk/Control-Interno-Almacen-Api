package com.ADS2.controlinternoalmacenapi.model;

import com.ADS2.controlinternoalmacenapi.model.enums.MemorandumStatus;
import com.ADS2.controlinternoalmacenapi.model.enums.MemorandumType;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "memorandum")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Memorandum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(100) DEFAULT 'PENDIENTE'")
    private MemorandumStatus status = MemorandumStatus.PENDIENTE;

    @Column(nullable = false)
    private String documentUrl;

    @Enumerated(EnumType.STRING)
    private MemorandumType type;

    @CreatedDate
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @ManyToOne
    @JoinColumn
    private Usuario assigned = null;
}

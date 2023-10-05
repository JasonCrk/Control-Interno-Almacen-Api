package com.ADS2.controlinternoalmacenapi.model;

import com.ADS2.controlinternoalmacenapi.model.enums.MemorandumStatus;

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

    @Enumerated(EnumType.STRING)
    private MemorandumStatus status = MemorandumStatus.PENDIENTE;

    @Column(nullable = false)
    private String documentUrl;

    @CreatedDate
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario user;
}

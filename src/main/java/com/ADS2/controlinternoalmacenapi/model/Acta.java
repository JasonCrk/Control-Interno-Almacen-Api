package com.ADS2.controlinternoalmacenapi.model;

import com.ADS2.controlinternoalmacenapi.model.enums.ActaType;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "acta")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Acta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String documentUrl;

    @Enumerated(EnumType.STRING)
    private ActaType type;

    @CreatedDate
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario user;
}

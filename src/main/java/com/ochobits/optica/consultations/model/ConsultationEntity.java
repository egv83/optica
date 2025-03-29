package com.ochobits.optica.consultations.model;

import com.ochobits.optica.medicalRecord.model.MedicalRecordEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "consultas")
public class ConsultationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "historia_clinica", referencedColumnName = "no_historia_clinica")
//    @Column(name = "historia_clinica",nullable = false)
    private MedicalRecordEntity historiaClinica;

    private LocalDateTime fecha;
    private String mc;
    private String cscod;
    private String cscoi;
    private String scod;
    private String scoi;
    private String usaod;
    private String usaoi;
    private String autorefod;
    private String autorefoi;
    private String usaadd;
    private String dp;
    private String od;
    private String oi;
    private String padd;
    private String vi;
    private String control;

}

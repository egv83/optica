package com.ochobits.optica.consultations.repository;

import com.ochobits.optica.consultations.dto.ConsultationDTO;

import java.util.List;

public interface ConsultationReadRepositoryService {

    List<ConsultationDTO> getAllConsultations();

    List<ConsultationDTO> findConsultationByMedicalRecord(Long medicalRecord);

}

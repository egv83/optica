package com.ochobits.optica.consultations.repository;

import com.ochobits.optica.consultations.dto.ConsultationDTO;

public interface ConsultationWriteRepositoryService {

    ConsultationDTO createConsultation(ConsultationDTO dto);

    ConsultationDTO updateConsultation(Long id, ConsultationDTO dto);

    void deleteConsultation(Long id);

}

package com.ochobits.optica.medicalRecord.service;

import com.ochobits.optica.medicalRecord.dto.MedicalRecordDTO;
import com.ochobits.optica.medicalRecord.dto.MedicalRecordMapper;
import com.ochobits.optica.medicalRecord.model.MedicalRecordEntity;
import com.ochobits.optica.medicalRecord.repository.JpaMedicalRecordRepository;
import com.ochobits.optica.medicalRecord.util.FindMedicalRecordSpecifications;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FindMedicalRecordService {

    private final JpaMedicalRecordRepository jpaMedicalRecordRepository;

    public FindMedicalRecordService(JpaMedicalRecordRepository jpaMedicalRecordRepository) {
        this.jpaMedicalRecordRepository = jpaMedicalRecordRepository;
    }

    public List<MedicalRecordDTO> getAllMedicalRecord(){
        return MedicalRecordMapper.toDTOList(jpaMedicalRecordRepository.findAll());
    }

    public List<MedicalRecordDTO> getMedicalRecord(String fullName){
        if(fullName.isBlank()){
            throw  new RuntimeException("Ingrese un nombre para la busqueda");
        }

        List<MedicalRecordEntity> medicalRecords = jpaMedicalRecordRepository.findAll(
                (Sort) FindMedicalRecordSpecifications.searchByFullName(fullName)
        );

        if(Objects.isNull(medicalRecords) || medicalRecords.isEmpty()){
            throw new RuntimeException("No existe historia clinica con ese nombre de paciente: "+fullName);
        }

        return MedicalRecordMapper.toDTOList(medicalRecords);

    }

}

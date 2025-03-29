package com.ochobits.optica.medicalRecord.dto;

import com.ochobits.optica.medicalRecord.model.MedicalRecordEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MedicalRecordMapper {

    public static MedicalRecordDTO toDTO(MedicalRecordEntity entity) {
        return new MedicalRecordDTO(
                entity.getId(),
                entity.getPrimerApellido(),
                entity.getSegundoApellido(),
                entity.getNombre(),
                entity.getCiudad(),
                entity.getOcupacion(),
                entity.getEdad(),
                entity.getEmail(),
                entity.getMc(),
                entity.getHea(),
                entity.getApp(),
                entity.getApf(),
                entity.getOjo(),
                entity.getMano(),
                entity.getDirecta(),
                entity.getInversa()
        );
    }

    public static MedicalRecordEntity toEntity(MedicalRecordDTO dto){
        return new MedicalRecordEntity(
                dto.id(),
                dto.primerApellido(),
                dto.segundoApellido(),
                dto.nombre(),
                dto.ciudad(),
                dto.ocupacion(),
                dto.edad(),
                dto.email(),
                dto.mc(),
                dto.hea(),
                dto.app(),
                dto.apf(),
                dto.ojo(),
                dto.mano(),
                dto.directa(),
                dto.inversa()
        );
    }

    public static MedicalRecordEntity updateToEntity(MedicalRecordEntity entity, MedicalRecordDTO dto){
        return new MedicalRecordEntity(
                entity.getId(),
                dto.primerApellido(),
                dto.segundoApellido(),
                dto.nombre(),
                dto.ciudad(),
                dto.ocupacion(),
                dto.edad(),
                dto.email(),
                dto.mc(),
                dto.hea(),
                dto.app(),
                dto.apf(),
                dto.ojo(),
                dto.mano(),
                dto.directa(),
                dto.inversa()
        );
    }

    public static List<MedicalRecordDTO> toDTOList(List<MedicalRecordEntity> entityList){
        return entityList.stream()
                .map(MedicalRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

}

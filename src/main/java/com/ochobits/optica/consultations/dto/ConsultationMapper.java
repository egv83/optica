package com.ochobits.optica.consultations.dto;

import com.ochobits.optica.consultations.model.ConsultationEntity;
import com.ochobits.optica.medicalRecord.model.MedicalRecordEntity;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ochobits.optica.Utils.Constants.YEAR_MONT_DATE_HOUR_MINUTS;
import static com.ochobits.optica.Utils.Constants.YEAR_MONT_DATE_HOUR_MINUTS_SECODS;
import static com.ochobits.optica.Utils.DateTimeUtil.convertStringToDateTime;

public class ConsultationMapper {

    public static ConsultationDTO toDTO(ConsultationEntity consultationEntity){
        return new ConsultationDTO(
                consultationEntity.getId(),
                Objects.isNull(consultationEntity.getHistoriaClinica()) ? null: consultationEntity.getHistoriaClinica().getId(),
                consultationEntity.getFecha().toString(),
                consultationEntity.getMc(),
                consultationEntity.getCscod(),
                consultationEntity.getCscoi(),
                consultationEntity.getScod(),
                consultationEntity.getScoi(),
                consultationEntity.getUsaod(),
                consultationEntity.getUsaoi(),
                consultationEntity.getAutorefod(),
                consultationEntity.getAutorefoi(),
                consultationEntity.getUsaadd(),
                consultationEntity.getDp(),
                consultationEntity.getOd(),
                consultationEntity.getOi(),
                consultationEntity.getPadd(),
                consultationEntity.getVi(),
                consultationEntity.getControl()
        );
    }

    public static ConsultationEntity toEntity(ConsultationDTO dto){
        return new ConsultationEntity().builder()
                .id(dto.id())
                .historiaClinica(Objects.isNull(dto.medicalRecord())? null : new MedicalRecordEntity(dto.medicalRecord()))
                .fecha(convertStringToDateTime(dto.fecha(),YEAR_MONT_DATE_HOUR_MINUTS))
                .mc(dto.mc())
                .cscod(dto.cscod())
                .cscoi(dto.cscoi())
                .scod(dto.scod())
                .scoi(dto.scoi())
                .usaod(dto.usaod())
                .usaoi(dto.usaoi())
                .autorefod(dto.autorefod())
                .autorefoi(dto.autorefoi())
                .usaadd(dto.usaadd())
                .dp(dto.dp())
                .od(dto.od())
                .oi(dto.oi())
                .padd(dto.padd())
                .vi(dto.vi())
                .control(dto.control())
                .build();
    }

    public static ConsultationEntity updateToEntity(ConsultationEntity entity,ConsultationDTO dto){
        return new ConsultationEntity().builder()
                .id(entity.getId())
                .historiaClinica(new MedicalRecordEntity(dto.medicalRecord()))
                .fecha(convertStringToDateTime(dto.fecha(),YEAR_MONT_DATE_HOUR_MINUTS))
                .mc(dto.mc())
                .cscod(dto.cscod())
                .cscoi(dto.cscoi())
                .scod(dto.scod())
                .scoi(dto.scoi())
                .usaod(dto.usaod())
                .usaoi(dto.usaoi())
                .autorefod(dto.autorefod())
                .autorefoi(dto.autorefoi())
                .usaadd(dto.usaadd())
                .dp(dto.dp())
                .od(dto.od())
                .oi(dto.oi())
                .padd(dto.padd())
                .vi(dto.vi())
                .control(dto.control())
                .build();
    }

    public static List<ConsultationDTO> toDTOList(List<ConsultationEntity> entityList){
        return entityList.stream()
                .map(ConsultationMapper::toDTO)
                .collect(Collectors.toList());
    }

}

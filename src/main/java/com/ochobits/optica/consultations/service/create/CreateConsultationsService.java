package com.ochobits.optica.consultations.service.create;

import com.ochobits.optica.consultations.dto.ConsultationDTO;
import com.ochobits.optica.consultations.dto.ConsultationMapper;
import com.ochobits.optica.consultations.model.ConsultationEntity;
import com.ochobits.optica.consultations.repository.database.JpaConsultationRepository;
import com.ochobits.optica.medicalRecord.repository.JpaMedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.ochobits.optica.Utils.Constants.YEAR_MONT_DATE_HOUR_MINUTS;
import static com.ochobits.optica.Utils.DateTimeUtil.convertStringToDateTime;

@Service
public class CreateConsultationsService {

    private final JpaConsultationRepository jpaConsultationRepository;
    private final JpaMedicalRecordRepository jpaMedicalRecordRepository;

    public CreateConsultationsService(JpaConsultationRepository jpaConsultationRepository, JpaMedicalRecordRepository jpaMedicalRecordRepository) {
        this.jpaConsultationRepository = jpaConsultationRepository;
        this.jpaMedicalRecordRepository = jpaMedicalRecordRepository;
    }

    public ConsultationDTO save(ConsultationDTO dto) {
        //PENDIENTE VALIDACION DE FECHA HORA
        var time = LocalDateTime.now();
//        System.out.println("ZONA HORATIA: "+ ZoneId.systemDefault());
//        var historiaClinicaOpt = jpaConsultationRepository.findByFechaAndMedicalRecordId(dto.medicalRecord(), dto.fecha());
//
//        if(!historiaClinicaOpt.isEmpty()){
//            throw new RuntimeException("La consulta con la fecha asignada e historia clinica ya existe");
//        }
//
        if(convertStringToDateTime(dto.fecha(),YEAR_MONT_DATE_HOUR_MINUTS).isAfter(time)){
            throw new RuntimeException("No puede ingresar una fecha antes a la de hoy");
        }
//
//        if(convertStringToDateTime(dto.fecha(),YEAR_MONT_DATE_HOUR_MINUTS).isEqual(time)){
//            throw new RuntimeException("No puede ingresar una fecha igual a la de hoy");
//        }

        return ConsultationMapper.toDTO(jpaConsultationRepository.save(ConsultationMapper.toEntity(dto)));
    }

    public ConsultationDTO update(Long id, ConsultationDTO dto){
        var time = LocalDateTime.now();
        var consultationOpt = jpaConsultationRepository.findById(id);
        if(consultationOpt.isEmpty()){
            throw new RuntimeException("No existe la consulta con el ID: "+id);
        }

//        if(convertStringToDateTime(dto.fecha(),YEAR_MONT_DATE_HOUR_MINUTS).isAfter(time)){
//            throw new RuntimeException("No puede ingresar una fecha antes a la de hoy");
//        }

//        if(convertStringToDateTime(dto.fecha(),YEAR_MONT_DATE_HOUR_MINUTS).isEqual(time)){
//            throw new RuntimeException("No puede ingresar una fecha igual a la de hoy");
//        }

        ConsultationEntity entity = consultationOpt.get();
        return ConsultationMapper.toDTO(jpaConsultationRepository.save(ConsultationMapper.updateToEntity(entity,dto)));

    }

    public void delete(Long id){

        var consultationOpt = jpaConsultationRepository.findById(id);
        if (consultationOpt.isEmpty()){
            throw new RuntimeException("No se encontro Consulta con el ID: "+id);
        }

        ConsultationEntity entity = consultationOpt.get();
        jpaConsultationRepository.delete(entity);

    }

}

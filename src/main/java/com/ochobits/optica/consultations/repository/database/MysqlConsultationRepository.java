package com.ochobits.optica.consultations.repository.database;

import com.ochobits.optica.consultations.dto.ConsultationDTO;
import com.ochobits.optica.consultations.dto.ConsultationMapper;
import com.ochobits.optica.consultations.model.ConsultationEntity;
import com.ochobits.optica.consultations.repository.ConsultationReadRepositoryService;
import com.ochobits.optica.consultations.repository.ConsultationWriteRepositoryService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MysqlConsultationRepository implements ConsultationReadRepositoryService, ConsultationWriteRepositoryService {
//    implements ConsultationRepository

    private final JpaConsultationRepository jpaConsultationRepository;

    public MysqlConsultationRepository(JpaConsultationRepository jpaConsultationRepository) {
        this.jpaConsultationRepository = jpaConsultationRepository;
    }

//    @Override
//    public List<Consultas> findConsultasByHistoriaClinica(Long historiaClinica) {
//
////        List<ConsultasEntity> results = jpaConsultasRepository.findByHistoriaClinica_Id(historiaClinica);
//        List<ConsultationEntity> results = jpaConsultationRepository.findByHistoriaClinicaId(historiaClinica);
//
//        List<Consultas> listaConsultas = results.stream()
//                .map(entity-> new Consultas(
//                        entity.getId(),
//                        entity.getHistoriaClinica() != null ?entity.getHistoriaClinica().getId() : null,
//                        entity.getFecha(),
//                        entity.getMc(),
//                        entity.getCscod(),
//                        entity.getUsaoi(),
//                        entity.getScod(),
//                        entity.getScoi(),
//                        entity.getUsaod(),
//                        entity.getUsaoi(),
//                        entity.getAutorefod(),
//                        entity.getAutorefoi(),
//                        entity.getUsaadd(),
//                        entity.getDp(),
//                        entity.getOd(),
//                        entity.getOi(),
//                        entity.getPadd(),
//                        entity.getVi(),
//                        entity.getControl()
//                  ))
//                .collect(Collectors.toList());
//
//        return listaConsultas;
//    }
//
//    @Override
//    public List<Consultas> getAllConsultas() {
//        List<ConsultationEntity> results = jpaConsultationRepository.findAll();
//
//        return results.stream()
//                .map(entity-> new Consultas(
//                        entity.getId(),
//                        entity.getHistoriaClinica() != null ? entity.getHistoriaClinica().getId() : null,
//                        entity.getFecha(),
//                        entity.getMc(),
//                        entity.getCscod(),
//                        entity.getUsaoi(),
//                        entity.getScod(),
//                        entity.getScoi(),
//                        entity.getUsaod(),
//                        entity.getUsaoi(),
//                        entity.getAutorefod(),
//                        entity.getAutorefoi(),
//                        entity.getUsaadd(),
//                        entity.getDp(),
//                        entity.getOd(),
//                        entity.getOi(),
//                        entity.getPadd(),
//                        entity.getVi(),
//                        entity.getControl()
//                ))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void save(ConsultasRequest consultasRequest)
//    {
//        jpaConsultationRepository.save(toEntity(consultasRequest));
//    }

//    private ConsultationEntity toEntity(ConsultasRequest consultasRequest){
//
//        return new ConsultationEntity(
//                jpaConsultationRepository.findMaxId(),
//                new HistoriaClinicaEntity(consultasRequest.historiaClinica()),
//                consultasRequest.fecha(),
//                consultasRequest.mc(),
//                consultasRequest.cscod(),
//                consultasRequest.cscoi(),
//                consultasRequest.scod(),
//                consultasRequest.scoi(),
//                consultasRequest.usaod(),
//                consultasRequest.usaoi(),
//                consultasRequest.autorefod(),
//                consultasRequest.autorefoi(),
//                consultasRequest.usaadd(),
//                consultasRequest.dp(),
//                consultasRequest.od(),
//                consultasRequest.oi(),
//                consultasRequest.padd(),
//                consultasRequest.vi(),
//                consultasRequest.control()
//        );
//    }

    @Override
    public List<ConsultationDTO> getAllConsultations() {
        return jpaConsultationRepository.findAll().stream()
                .map(ConsultationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationDTO> findConsultationByMedicalRecord(Long medicalRecord) {
//        List<ConsultationEntity> consultations = jpaConsultationRepository.findByMedicalRecordId(medicalRecord);

        return jpaConsultationRepository.findByMedicalRecordId(medicalRecord).stream()
                .map(ConsultationMapper::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public ConsultationDTO createConsultation(ConsultationDTO dto) {
        ConsultationEntity entity = ConsultationMapper.toEntity(dto);
        return ConsultationMapper.toDTO(jpaConsultationRepository.save(entity));
    }

    @Override
    public ConsultationDTO updateConsultation(Long id, ConsultationDTO dto) {

        return null;
    }

    @Override
    public void deleteConsultation(Long id) {
        jpaConsultationRepository.deleteById(id);
    }
}

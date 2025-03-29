package com.ochobits.optica.consultations.service.find;

import com.ochobits.optica.consultations.dto.ConsultationDTO;
import com.ochobits.optica.consultations.dto.ConsultationMapper;
import com.ochobits.optica.consultations.repository.ConsultationReadRepositoryService;
import com.ochobits.optica.consultations.repository.ConsultationRepository;
//import com.ochobits.optica.shared.dto.Consultas;
import com.ochobits.optica.consultations.repository.database.JpaConsultationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindConsultationsService {

//    private final ConsultationRepository consultationRepository;
//    public FindConsultationsService(ConsultationRepository consultationRepository) {
//        this.consultationRepository = consultationRepository;
//    }

    /**
     * INTERFACE TEMPORAL PARA MODO HEXADECIMAL
     */
//    private final ConsultationReadRepositoryService consultationReadRepositoryService;
//    public FindConsultationsService( ConsultationReadRepositoryService consultationReadRepositoryService) {
//        this.consultationReadRepositoryService = consultationReadRepositoryService;
//    }


    private final JpaConsultationRepository jpaConsultationRepository;

    public FindConsultationsService(JpaConsultationRepository jpaConsultationRepository) {
        this.jpaConsultationRepository = jpaConsultationRepository;
    }


//
//    public List<Consultas> getConsultasByHistoriaClinica(Long historiaClinica){
//        var consulta = consultationRepository.findConsultasByHistoriaClinica(historiaClinica);
//
//        if(consulta.isEmpty()){
//            throw new RuntimeException("No se encontro consultas con ese número de historia clinica");
//        }
//
//        List<Consultas> results = new ArrayList<>();
//        consulta.stream()
//                .forEach(c-> results.add(
//                        new Consultas(
//                                c.getCodigo(),
//                                c.getHistoriaClinica(),
//                                c.getFecha(),
//                                c.getMc(),
//                                c.getCscod(),
//                                c.getUsaoi(),
//                                c.getScod(),
//                                c.getScoi(),
//                                c.getUsaod(),
//                                c.getUsaoi(),
//                                c.getAutorefod(),
//                                c.getAutorefoi(),
//                                c.getUsaadd(),
//                                c.getDp(),
//                                c.getOd(),
//                                c.getOi(),
//                                c.getPadd(),
//                                c.getVi(),
//                                c.getControl()
//                        )
//                ));
//
//        return results;
//    }

//    public List<ConsultationDTO> getAllConsultations(){
//        var results = consultationRepository.getAllConsultas();
//
//        if (results.isEmpty()){
//            throw new RuntimeException("No se encontro consultas");
//        }
//
//        return results;
//    }

    /**
     * OBTENER CONSULTA POR HISTORIA CLINICA
     *
     */
//    public List<ConsultationDTO> getConsultasByHistoriaClinica(Long medicalRecord){
//
//        var results = consultationReadRepositoryService.findConsultationByMedicalRecord(medicalRecord);
//
//        if(results.isEmpty()){
//            throw new RuntimeException("No se encontro consultas con ese número de historia clinica");
//        }
//
//        return results;

//        List<Consultas> results = new ArrayList<>();
//        consulta.stream()
//                .forEach(c-> results.add(
//                        new Consultas(
//                                c.getCodigo(),
//                                c.getHistoriaClinica(),
//                                c.getFecha(),
//                                c.getMc(),
//                                c.getCscod(),
//                                c.getUsaoi(),
//                                c.getScod(),
//                                c.getScoi(),
//                                c.getUsaod(),
//                                c.getUsaoi(),
//                                c.getAutorefod(),
//                                c.getAutorefoi(),
//                                c.getUsaadd(),
//                                c.getDp(),
//                                c.getOd(),
//                                c.getOi(),
//                                c.getPadd(),
//                                c.getVi(),
//                                c.getControl()
//                        )
//                ));
//
//        return results;
//    }

    /**
     * OBTENER TODAS LAS CONSULTAS
     * @return
     */
//    public List<ConsultationDTO> getAllConsultations(){
//        var results = consultationRepository.getAllConsultas();
//        var results = consultationReadRepositoryService.getAllConsultations();
//
//        if (results.isEmpty()){
//            throw new RuntimeException("No se encontro consultas");
//        }
//
//        return results;
//    }

    public List<ConsultationDTO> getConsultasByMedicalRecord(Long medicalRecord){
        var consultation = jpaConsultationRepository.findByMedicalRecordId(medicalRecord);

        if(consultation.isEmpty()){
            throw new RuntimeException("No se encontro consultas con ese número de historia clinica");
        }

        return ConsultationMapper.toDTOList(consultation);
    }

    public List<ConsultationDTO> getAllConsultations(){
        return ConsultationMapper.toDTOList(jpaConsultationRepository.findAll());
    }

}

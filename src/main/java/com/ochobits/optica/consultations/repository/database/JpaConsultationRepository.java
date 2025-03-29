package com.ochobits.optica.consultations.repository.database;

import com.ochobits.optica.consultations.model.ConsultationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaConsultationRepository extends JpaRepository<ConsultationEntity, Long>  {

    @Query(value = "SELECT COALESCE(MAX(c.id),0)+1 FROM consultas c ORDER BY c.id DESC",nativeQuery = true)
    Long findMaxId();

//    List<ConsultasEntity> findByHistoriaClinica(HistoriaClinicaEntity historiaClinica);

//    @Query("SELECT c FROM ConsultasEntity c WHERE (:historiaClinicaId IS NULL OR c.historiaClinica.id = :historiaClinicaId)")
//    List<ConsultationEntity> findByHistoriaClinicaId(@Param("historiaClinicaId")Long historiaClinica);

    @Query("SELECT c FROM ConsultationEntity c WHERE (:historiaClinicaId IS NULL OR c.historiaClinica.id = :historiaClinicaId)")
    List<ConsultationEntity> findByMedicalRecordId(@Param("historiaClinicaId")Long medicalRecord);

//    List<ConsultasEntity> findByHistoriaClinica_Id(Long historiaClinica);

//    @Query("SELECT c FROM ConsultationEntity c WHERE (:historiaClinicaId IS NULL OR c.historiaClinica.id = :historiaClinicaId) AND c.fecha = :fecha")
    @Query(value = "SELECT * FROM consultas c WHERE (:historiaClinicaId IS NULL OR c.historia_clinica = :historiaClinicaId) " +
            "AND c.fecha LIKE CONCAT(:fecha, '%')",
    nativeQuery = true)
    List<ConsultationEntity> findByFechaAndMedicalRecordId(@Param("historiaClinicaId")Long medicalRecord, @Param("fecha")String fecha);

}

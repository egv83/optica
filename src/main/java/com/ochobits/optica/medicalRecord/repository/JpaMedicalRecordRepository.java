package com.ochobits.optica.medicalRecord.repository;

import com.ochobits.optica.medicalRecord.model.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaMedicalRecordRepository extends JpaRepository<MedicalRecordEntity,Long> {

    @Query(value = "SELECT COALESCE(MAX(h.no_historia_clinica),0)+1 FROM historiaclinica h ORDER BY h.no_historia_clinica DESC",nativeQuery = true)
    Long findMaxId();
    Optional<MedicalRecordEntity> findByNombreAndPrimerApellidoAndSegundoApellido(String primerApellido, String segundoAPellido, String nombre);

}

package deprecated.historiaClinica.repository;

import com.ochobits.optica.medicalRecord.model.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaHistoriaClinicaRepository extends JpaRepository<MedicalRecordEntity, Long>, JpaSpecificationExecutor<MedicalRecordEntity> {

//    @Query("SELECT IFNULL(MAX(h.noHistoriaClinica),0)+1 FROM HistoriaClinicaEntity h")
    @Query(value = "SELECT COALESCE(MAX(h.no_historia_clinica),0)+1 FROM historiaclinica h ORDER BY h.no_historia_clinica DESC",nativeQuery = true)
    Long findMaxId();

    Optional<MedicalRecordEntity> findById(Long id);
    Optional<MedicalRecordEntity> findByNombre(String nombre);
    Optional<MedicalRecordEntity> findByPrimerApellido(String primerApellido);
    Optional<MedicalRecordEntity> findBySegundoApellido(String segundoApellido);

    Optional<MedicalRecordEntity> findByNombreAndPrimerApellidoAndSegundoApellido(
            String nombre, String primerApellido, String segundoApellido);

    @Query("SELECT h FROM HistoriaClinicaEntity h WHERE h.nombre = :nombre " +
            "AND h.primerApellido = :primerApellido AND h.segundoApellido = :segundoApellido")
    Optional<MedicalRecordEntity> findSimilar(
            @Param("nombre") String nombre,
            @Param("primerApellido") String primerApellido,
            @Param("segundoApellido") String segundoApellido
    );
}
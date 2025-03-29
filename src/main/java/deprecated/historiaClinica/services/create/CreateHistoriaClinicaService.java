package deprecated.historiaClinica.services.create;

import deprecated.historiaClinica.exceptions.HistoriaClinicaException;
import com.ochobits.optica.shared.dto.HistoriaClinica;
import com.ochobits.optica.medicalRecord.model.MedicalRecordEntity;
import deprecated.historiaClinica.repository.JpaHistoriaClinicaRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateHistoriaClinicaService {

    private final JpaHistoriaClinicaRepository jpaHistoriaClinicaRepository;


    public CreateHistoriaClinicaService(JpaHistoriaClinicaRepository jpaHistoriaClinicaRepository) {
        this.jpaHistoriaClinicaRepository = jpaHistoriaClinicaRepository;
    }

    public HistoriaClinica execute(HistoriaClinica historiaClinica) {

        var historiaOPT = jpaHistoriaClinicaRepository.findByNombreAndPrimerApellidoAndSegundoApellido(
                historiaClinica.nombre(), historiaClinica.primerApellido(), historiaClinica.segundoApellido()
        );

        if (historiaOPT.isPresent()) {
            throw new HistoriaClinicaException("El nombre o apellidos del pasiente ya existe");
        }
        if (historiaClinica.nombre().isBlank()) {
            throw new HistoriaClinicaException("Por favor ingrese el nombre");
        }
        if (historiaClinica.primerApellido().isBlank()) {
            throw new HistoriaClinicaException("Por favor ingrese el apellido paterno");
        }
        if (historiaClinica.ciudad().isBlank()) {
            throw new HistoriaClinicaException("Por favor ingrese la ciudad");
        }
        if (historiaClinica.edad().isBlank()) {
            throw new HistoriaClinicaException("Por favor ingrese la edad");
        }

        try {
            var clinicaEntity = MedicalRecordEntity.builder()
                    .id(jpaHistoriaClinicaRepository.findMaxId())
                    .nombre(historiaClinica.nombre())
                    .primerApellido(historiaClinica.primerApellido())
                    .segundoApellido(historiaClinica.segundoApellido())
                    .ciudad(historiaClinica.ciudad())
                    .ocupacion(historiaClinica.ocupacion())
                    .edad(historiaClinica.edad())
                    .email(historiaClinica.email())
                    .mc(historiaClinica.mc()).hea(historiaClinica.hea())
                    .app(historiaClinica.app()).apf(historiaClinica.apf())
                    .ojo(historiaClinica.ojo()).mano(historiaClinica.mano())
                    .directa(historiaClinica.directa()).inversa(historiaClinica.inversa())
                    .build();

            var obj = jpaHistoriaClinicaRepository.save(clinicaEntity);
            return new HistoriaClinica(
                    obj.getId(),
                    null,
                    null,
                    null,
                    null, null,
                    null, null, null
                    , null, null, null
                    , null, null, '\u0000'
                    , '\u0000'
            );
        } catch (Exception e) {
            throw new HistoriaClinicaException("Error al guardar la historia clinica: " + e.getMessage());
        }

    }

}

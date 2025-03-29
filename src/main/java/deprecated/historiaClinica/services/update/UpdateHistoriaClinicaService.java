package deprecated.historiaClinica.services.update;

import deprecated.historiaClinica.exceptions.HistoriaClinicaException;
import com.ochobits.optica.shared.dto.HistoriaClinica;
import com.ochobits.optica.medicalRecord.model.MedicalRecordEntity;
import deprecated.historiaClinica.repository.JpaHistoriaClinicaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateHistoriaClinicaService {
    private final Logger logger = LoggerFactory.getLogger(UpdateHistoriaClinicaService.class);

    private final JpaHistoriaClinicaRepository jpaHistoriaClinicaRepository;


    public UpdateHistoriaClinicaService(JpaHistoriaClinicaRepository jpaHistoriaClinicaRepository) {
        this.jpaHistoriaClinicaRepository = jpaHistoriaClinicaRepository;
    }

//    public HistoriaClinica execute(HistoriaClinica historiaClinica) {
//        var historiaOPT = jpaHistoriaClinicaRepository.findById(historiaClinica.id());
//        if (historiaOPT.isEmpty()) {
//            logger.error("Update Historia Clinica no encontrado con id: " + historiaClinica.id().toString());
//            throw new HistoriaClinicaException("Update Historia Clinica no encontrado con id: "
//                    + historiaClinica.id().toString());
//        }
//        try {
//            logger.info("Historia Clinica encontrada id: " + historiaClinica.id());
//            var clinicaEntity = HistoriaClinicaEntity.builder()
//                    .id(historiaClinica.id())
//                    .nombre(historiaClinica.nombre())
//                    .primerApellido(historiaClinica.primerApellido())
//                    .segundoApellido(historiaClinica.segundoApellido())
//                    .ciudad(historiaClinica.ciudad())
//                    .ocupacion(historiaClinica.ocupacion())
//                    .edad(historiaClinica.edad())
//                    .email(historiaClinica.email())
//                    .mc(historiaClinica.mc()).hea(historiaClinica.hea())
//                    .app(historiaClinica.app()).apf(historiaClinica.apf())
//                    .ojo(historiaClinica.ojo()).mano(historiaClinica.mano())
//                    .directa(historiaClinica.directa()).inversa(historiaClinica.inversa())
//                    .build();
//            var obj = jpaHistoriaClinicaRepository.save(clinicaEntity);
//            return new HistoriaClinica(
//                    obj.getId(),
//                    obj.getNombre(),
//                    obj.getPrimerApellido(),
//                    obj.getSegundoApellido(),
//                    obj.getCiudad(), obj.getOcupacion(),
//                    obj.getEdad(), obj.getEmail(),
//                    obj.getMc(), obj.getHea(), obj.getApp(),
//                    obj.getApf(), obj.getOjo(), obj.getMano(),
//                    obj.getDirecta(), obj.getInversa()
//            );
//        } catch (Exception e) {
//            throw new HistoriaClinicaException("Error updating Historia Clinica: " + e.getMessage());
//        }
//
//    }

    public void execute(HistoriaClinica historiaClinica) {

        /*BUSCAR SOLO POR IR*/
        var historiaOPT = jpaHistoriaClinicaRepository.findById(historiaClinica.id());
        if (historiaOPT.isEmpty()) {
            logger.error("Update Historia Clinica no encontrado con id: " + historiaClinica.id().toString());
            throw new HistoriaClinicaException("Update Historia Clinica no encontrado con id: "
                    + historiaClinica.id().toString());
        }

        var historia = findHistoriaClinica(historiaClinica);
        if (historia.isPresent()) {
            String message = String.format("Update Historia Clinica ya existe ese paciente con nombre: %s, " +
                    "primer apellido: %s, segundo apellido: %s", historiaClinica.nombre(),
                    historiaClinica.primerApellido(), historiaClinica.segundoApellido());
            logger.error(message);
            throw new HistoriaClinicaException(message);
        }

        try {
            logger.info("Historia Clinica encontrada id: " + historiaClinica.id());
            var clinicaEntity = MedicalRecordEntity.builder()
                    .id(historiaClinica.id())
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
            jpaHistoriaClinicaRepository.save(clinicaEntity);
        } catch (Exception e) {
            throw new HistoriaClinicaException("Error updating Historia Clinica: " + e.getMessage());
        }

    }

    private Optional<MedicalRecordEntity> findHistoriaClinica(HistoriaClinica historiaClinica) throws RuntimeException{
        return jpaHistoriaClinicaRepository.findSimilar(
                historiaClinica.nombre(),
                historiaClinica.primerApellido(),
                historiaClinica.segundoApellido()
        );
    }

}

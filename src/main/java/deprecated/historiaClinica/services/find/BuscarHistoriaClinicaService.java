package deprecated.historiaClinica.services.find;

import com.ochobits.optica.Utils.PageDetails;
import com.ochobits.optica.Utils.response.ResultsPage;
import deprecated.historiaClinica.exceptions.HistoriaClinicaException;
import com.ochobits.optica.shared.dto.HistoriaClinica;
import com.ochobits.optica.medicalRecord.model.MedicalRecordEntity;
import deprecated.historiaClinica.repository.BuscarHistoriaClinicaSpecifications;
import deprecated.historiaClinica.repository.JpaHistoriaClinicaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BuscarHistoriaClinicaService {

    private final JpaHistoriaClinicaRepository jpaHistoriaClinicaRepository;
    PageDetails pageDetails;

    public BuscarHistoriaClinicaService(JpaHistoriaClinicaRepository jpaHistoriaClinicaRepository) {
        this.jpaHistoriaClinicaRepository = jpaHistoriaClinicaRepository;
    }

//    public Object buscarHistoriaClinicaAll(){
//
//        var historiaOpt = jpaHistoriaClinicaRepository.findAll();
////        if(Objects.isNull(historiaOpt) || historiaOpt.isEmpty()){
////            throw new HistoriaClinicaException("No existe consultas con ese nombre de paciente: "+fullName);
////        }
//
//        try{
//            List<HistoriaClinica> result = historiaOpt.stream()
//                    .map(entity -> new HistoriaClinica(
//                            entity.getId(),
//                            entity.getNombre(),
//                            entity.getPrimerApellido(),
//                            entity.getSegundoApellido(),
//                            entity.getCiudad(),
//                            entity.getOcupacion(),
//                            entity.getEdad(),
//                            entity.getEmail(),
//                            entity.getMc(),
//                            entity.getHea(),
//                            entity.getApp(),
//                            entity.getApf(),
//                            entity.getOjo(),
//                            entity.getMano(),
//                            entity.getDirecta(),
//                            entity.getInversa()
//                    )).toList();
//
//            return Response.withoutPagination(result).toDynamicMap(HistoriaClinica.class);
//        } catch (RuntimeException e){
//            return Response.withError(e.getMessage(),Optional.empty(),HistoriaClinica.class);
//        }
//    }

//    public Object buscarHistoriaClinica(String fullName){
//            if (fullName.isBlank()) {
//                throw new HistoriaClinicaException("Ingrese un nombre para la busqueda");
//            }
//
//            var historiaOpt = jpaHistoriaClinicaRepository.findAll(BuscarHistoriaClinicaSpecifications.searchByFullName(fullName));
//            if(Objects.isNull(historiaOpt) || historiaOpt.isEmpty()){
//                throw new HistoriaClinicaException("No existe consultas con ese nombre de paciente: "+fullName);
//            }
//
//            try{
//                List<HistoriaClinica> result = historiaOpt.stream()
//                        .map(entity -> new HistoriaClinica(
//                                entity.getId(),
//                                entity.getNombre(),
//                                entity.getPrimerApellido(),
//                                entity.getSegundoApellido(),
//                                entity.getCiudad(),
//                                entity.getOcupacion(),
//                                entity.getEdad(),
//                                entity.getEmail(),
//                                entity.getMc(),
//                                entity.getHea(),
//                                entity.getApp(),
//                                entity.getApf(),
//                                entity.getOjo(),
//                                entity.getMano(),
//                                entity.getDirecta(),
//                                entity.getInversa()
//                        )).toList();
//
//                return Response.withoutPagination(result).toDynamicMap(HistoriaClinica.class);
//            } catch (RuntimeException e){
//            return Response.withError(e.getMessage(),Optional.empty(),HistoriaClinica.class);
//        }
//    }

    public ResultsPage<HistoriaClinica> buscarHistoriaClinicaAll(){

        var historiaOpt = jpaHistoriaClinicaRepository.findAll();
        if(Objects.isNull(historiaOpt) || historiaOpt.isEmpty()){
            throw new HistoriaClinicaException("No existe historias clinicas");
        }

        try{
            List<HistoriaClinica> historiaClinica = historiaOpt.stream()
                    .map(entity -> new HistoriaClinica(
                            entity.getId(),
                            entity.getNombre(),
                            entity.getPrimerApellido(),
                            entity.getSegundoApellido(),
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
                    )).toList();

//            return Response.withoutPagination(result).toDynamicMap(HistoriaClinica.class);
            return new ResultsPage<>(
                    historiaClinica,
                    null
            );
        } catch (Exception e){
            throw new HistoriaClinicaException("Error al obtener la busqueda: "+e.getMessage());
        }
    }

//    public Object buscarHistoriaClinicaPage(String fullName, int page, int size){
    public ResultsPage<HistoriaClinica> buscarHistoriaClinicaPage(String fullName, int page, int size){
        try {
            if (fullName.isBlank()) {
                throw new HistoriaClinicaException("Ingrese un nombre para la busqueda");
            }

            Pageable pageable = PageRequest.of(page,size);
            Page<MedicalRecordEntity> resultPage = jpaHistoriaClinicaRepository.findAll(BuscarHistoriaClinicaSpecifications.searchByFullName(fullName), pageable);

            if (Objects.isNull(resultPage) || resultPage.isEmpty()){
                throw new HistoriaClinicaException("No existe consultas con ese nombre de paciente: "+fullName);
            }

            pageDetails = new PageDetails(
                    resultPage.getNumber(),
                    resultPage.getSize(),
                    resultPage.getTotalElements(),
                    resultPage.getTotalPages()
            );
            List<HistoriaClinica> historiaClinica = resultPage.getContent().stream()
                    .map(entity -> new HistoriaClinica(
                            entity.getId(),
                            entity.getNombre(),
                            entity.getPrimerApellido(),
                            entity.getSegundoApellido(),
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
                    )).toList();
//
//            return Response.withData(historiaClinica,pageDetails).toDynamicMap(HistoriaClinica.class);

            return new ResultsPage<>(
                    historiaClinica,
                    pageDetails
                    );

        } catch (Exception e) {
            throw new HistoriaClinicaException("Error al obtener la busqueda con paginación: "+e.getMessage());
        }

    }

}

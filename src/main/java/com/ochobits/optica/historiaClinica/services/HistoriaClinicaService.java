package com.ochobits.optica.historiaClinica.services;

import com.ochobits.optica.Utils.GlobalUtils;
import com.ochobits.optica.Utils.Response;
import com.ochobits.optica.historiaClinica.dto.HistoriaClinica;
import com.ochobits.optica.historiaClinica.dto.HistoriaClinicaResponse;
import com.ochobits.optica.historiaClinica.entities.HistoriaclinicaEntity;
import com.ochobits.optica.historiaClinica.repository.HistoriaclinicaSpecifications;
import com.ochobits.optica.historiaClinica.repository.JpaHistoriaclinicaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HistoriaClinicaService {

    private final JpaHistoriaclinicaRepository jpaHistoriaclinicaRepository;
    Response.PageDetails pageDetails;

    public HistoriaClinicaService(JpaHistoriaclinicaRepository jpaHistoriaclinicaRepository) {
        this.jpaHistoriaclinicaRepository = jpaHistoriaclinicaRepository;
    }

    public Object buscarHistoriaClinica(String fullName){
        try {
            if (fullName.isBlank()) {
                throw new RuntimeException("Ingrese un nombre para la busqueda");
            }

            var historiaOpt = jpaHistoriaclinicaRepository.findAll(HistoriaclinicaSpecifications.searchByFullName(fullName));
            if(Objects.isNull(historiaOpt) || historiaOpt.isEmpty()){
                throw new RuntimeException("No existe consultas con ese nombre de paciente: "+fullName);
            }

            List<HistoriaClinica> result = historiaOpt.stream()
                    .map(entity -> new HistoriaClinica(
                             entity.getNoHistoriaClinica(),
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
//            return HistoriaClinicaResponse.withoutPagina1hyaa pero tu estas con el metodoion(result).toMap();
//            return GlobalUtils.toMap(HistoriaClinicaResponse.withoutPagination(result));

//            return GlobalUtils.toMap(Response.withoutPagination(result));
            return Response.withoutPagination(result).toDynamicMap(HistoriaClinica.class);
        }catch (RuntimeException e){
//            return HistoriaClinicaResponse.withError(e.getMessage(),Optional.empty());
            return Response.withError(e.getMessage(),Optional.empty(),HistoriaClinica.class);
        }
    }

    public Object buscarHistoriaClinicaPage(String fullName, int page, int size){
        try {
            if (fullName.isBlank()) {
                throw new RuntimeException("Ingrese un nombre para la busqueda");
            }

            Pageable pageable = PageRequest.of(page,size);
            Page<HistoriaclinicaEntity> resultPage = jpaHistoriaclinicaRepository.findAll(HistoriaclinicaSpecifications.searchByFullName(fullName), pageable);

            if (Objects.isNull(resultPage) || resultPage.isEmpty()){
                throw new RuntimeException("No existe consultas con ese nombre de paciente: "+fullName);
            }

            pageDetails = new Response.PageDetails(
                    resultPage.getNumber(),
                    resultPage.getSize(),
                    resultPage.getTotalElements(),
                    resultPage.getTotalPages()
            );

            List<HistoriaClinica> historiaClinica = resultPage.getContent().stream()
                    .map(entity -> new HistoriaClinica(
                            entity.getNoHistoriaClinica(),
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

            return Response.withData(historiaClinica,pageDetails).toDynamicMap(HistoriaClinica.class);
//            return HistoriaClinicaResponse.withData(historiaClinica,pageDetails).toMap();

//            return GlobalUtils.toMap(HistoriaClinicaResponse.withData(historiaClinica,pageDetails));

//            return GlobalUtils.toMap(Response.withData(historiaClinica,pageDetails));

//            return GlobalUtils.toMap(Response.withData(historiaClinica,pageDetails));
//            return resultPage.map(entity-> {
//                HistoriaClinicaResponse historia = HistoriaClinicaResponse.withData(
//                        entity.getNoHistoriaClinica(),
//                        entity.getNombre(),
//                        entity.getPrimerApellido(),
//                        entity.getSegundoApellido(),
//                        entity.getCiudad(),
//                        entity.getOcupacion(),
//                        entity.getEdad(),
//                        entity.getEmail(),
//                        entity.getMc(),
//                        entity.getHea(),
//                        entity.getApp(),
//                        entity.getApf(),
//                        entity.getOjo(),
//                        entity.getMano(),
//                        entity.getDirecta(),
//                        entity.getInversa()
//                );
//                return historia;
//            });

        }catch (RuntimeException e){
//            pageDetails = new HistoriaClinicaResponse.PageDetails(
//                page,size,0,0
//            );
            return Response.withError(e.getMessage(), Optional.empty(),HistoriaClinica.class);
        }
    }

}

package com.ochobits.optica.consultations.controller;

import com.ochobits.optica.consultations.dto.ConsultationDTO;
import com.ochobits.optica.consultations.service.create.CreateConsultationsService;
import com.ochobits.optica.consultations.service.find.FindConsultationsService;
import com.ochobits.optica.shared.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/consulta")
public class ConsultationController {

    private final FindConsultationsService findConsultationsService;
    private final CreateConsultationsService createConsultationsService;

    public ConsultationController(FindConsultationsService findConsultationsService, CreateConsultationsService createConsultationsService) {
        this.findConsultationsService = findConsultationsService;
        this.createConsultationsService = createConsultationsService;
    }

//    @GetMapping
//    public ResponseEntity<List<Consultas>> getAllConultas(){
//
//        var response = findConsultasService.getAllConsultas();
//
//        return ResponseEntity.ok(response);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<List<Consultas>> getConsultasByHistoriaClinica(
//            @PathVariable(name = "id") Long historiaClinica,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ){
//
//        var response = findConsultasService.getConsultasByHistoriaClinica(historiaClinica);
//
//        return ResponseEntity.ok(response);
//    }

    @GetMapping()
    public ResponseEntity<List<ConsultationDTO>> getConsultasByMedicalRecord(
            @RequestParam(required = false) Long medicalRecord,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(findConsultationsService.getConsultasByMedicalRecord(medicalRecord),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ConsultationDTO> createConsultation(@RequestBody ConsultationDTO request) {
        return new ResponseEntity<>(createConsultationsService.save(request),HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ConsultationDTO> updateCpnsultation(
            @PathVariable("id") Long id,
            @RequestBody ConsultationDTO request
    ){
        return new ResponseEntity<>(createConsultationsService.update(id,request),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable("id") Long id){
        if(Objects.isNull(id) || id <= 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            createConsultationsService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

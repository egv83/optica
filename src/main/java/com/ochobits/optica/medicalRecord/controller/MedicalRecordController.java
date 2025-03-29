package com.ochobits.optica.medicalRecord.controller;

import com.ochobits.optica.medicalRecord.dto.MedicalRecordDTO;
import com.ochobits.optica.medicalRecord.service.CreateMedicalRecordService;
import com.ochobits.optica.medicalRecord.service.FindMedicalRecordService;
import com.ochobits.optica.shared.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/medical-record")
public class MedicalRecordController {

    private final FindMedicalRecordService findMedicalRecordService;
    private final CreateMedicalRecordService createMedicalRecordService;

    public MedicalRecordController(FindMedicalRecordService findMedicalRecordService, CreateMedicalRecordService createMedicalRecordService) {
        this.findMedicalRecordService = findMedicalRecordService;
        this.createMedicalRecordService = createMedicalRecordService;
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecordDTO>> getAllMedicalRecords(){
        return new ResponseEntity<>(findMedicalRecordService.getAllMedicalRecord(), HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<List<MedicalRecordDTO>> getMedicalRecodByName(@RequestParam String name){
        return new ResponseEntity<>(findMedicalRecordService.getMedicalRecord(name),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicalRecordDTO> createMedicalRecord(@RequestBody MedicalRecordDTO request){
        return new ResponseEntity<>(createMedicalRecordService.save(request),HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MedicalRecordDTO> updateMedicalRecord(@RequestParam Long id, @RequestBody MedicalRecordDTO request){
        return new ResponseEntity<>(createMedicalRecordService.update(id,request),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@RequestParam Long id){
        if(Objects.isNull(id) || id <= 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            createMedicalRecordService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ResourceNotFoundException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

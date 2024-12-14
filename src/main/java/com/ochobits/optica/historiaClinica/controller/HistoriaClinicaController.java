package com.ochobits.optica.historiaClinica.controller;

import com.ochobits.optica.athentication.security.config.OpticaUserDetails;
import com.ochobits.optica.historiaClinica.dto.HistoriaClinicaRequest;
import com.ochobits.optica.historiaClinica.services.HistoriaClinicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/historia_clinica")
public class HistoriaClinicaController {

    private final HistoriaClinicaService historiaClinicaService;

    public HistoriaClinicaController(HistoriaClinicaService historiaClinicaService) {
        this.historiaClinicaService = historiaClinicaService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllHistoriasClinicas(
            @RequestParam String fullname,
            @RequestParam(defaultValue= "0") int page,
            @RequestParam(defaultValue= "10") int size,
            Authentication authentication
    ){
        OpticaUserDetails userDetails = (OpticaUserDetails) authentication.getPrincipal();

        HistoriaClinicaRequest request = new HistoriaClinicaRequest(
                fullname
        );
        Object response = historiaClinicaService.buscarHistoriaClinica(request.nombre());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getHistoriaClinica")
    public ResponseEntity<Object> getAllHistoriasClinicasPaged(
            @RequestParam String fullname,
            @RequestParam(defaultValue= "0") int page,
            @RequestParam(defaultValue= "10") int size,
            Authentication authentication
    ){
        OpticaUserDetails userDetails = (OpticaUserDetails) authentication.getPrincipal();

        HistoriaClinicaRequest request = new HistoriaClinicaRequest(
                fullname
        );

        Object responses = historiaClinicaService.buscarHistoriaClinicaPage(request.nombre(),page,size);
        return ResponseEntity.ok(responses);
    }
}

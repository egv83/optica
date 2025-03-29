package com.ochobits.optica.Utils.errorHandler;

import deprecated.historiaClinica.exceptions.HistoriaClinicaException;
import com.ochobits.optica.shared.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HistoriaClinicaException.class)
    public ResponseEntity<Map<String,Object>> handleHistoriaClinicaException(HistoriaClinicaException ex){
        Map<String,Object> response = new HashMap<>();
        response.put("error","Historia Clinica Error");
        response.put("message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MedicoException.class)
    public ResponseEntity<Map<String,Object>> handleMedicoException(MedicoException ex){
        Map<String,Object> response = new HashMap<>();
        response.put("error","Meddico Error");
        response.put("message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public ResponseEntity<Map<String,Object>> handleGenericException(Exception ex){
        Map<String,Object> response = new HashMap<>();
        response.put("error","Internal Server Error");
        response.put("message","Ocurrio un error inesperado: "+ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}

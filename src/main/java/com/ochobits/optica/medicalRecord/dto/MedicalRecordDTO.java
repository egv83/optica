package com.ochobits.optica.medicalRecord.dto;

public record MedicalRecordDTO(
        Long id,
        String primerApellido,
        String segundoApellido,
        String nombre,
        String ciudad,
        String ocupacion,
        String edad,
        String email,
        String mc,
        String hea,
        String app,
        String apf,
        String ojo,
        String mano,
        char directa,
        char inversa
) {
}

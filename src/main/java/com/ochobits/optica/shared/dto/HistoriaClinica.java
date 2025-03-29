package com.ochobits.optica.shared.dto;

public record HistoriaClinica(
        Long id,
        String nombre,
        String primerApellido,
        String segundoApellido,
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

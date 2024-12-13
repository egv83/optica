package com.ochobits.optica.historiaClinica.dto;

public record HistoriaClinica(
        Long noHistoriaClinica,
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
        String directa,
        String inversa
) {

}

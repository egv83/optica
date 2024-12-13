package com.ochobits.optica.historiaClinica.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class HistoriaClinica {

    private Integer noHistoriaClinica;
    private String primerApellido;
    private String segundoApellido;
    private String nombre;
    private String ciudad;
    private String ocupacion;
    private String edad;
    private String email;
    private String mc;
    private String hea;
    private String app;
    private String apf;
    private String ojo;
    private String mano;
    private String directa;
    private String inversa;

}

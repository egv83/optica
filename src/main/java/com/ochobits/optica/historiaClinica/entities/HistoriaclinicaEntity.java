package com.ochobits.optica.historiaClinica.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "HISTORIACLINICA")
public class HistoriaclinicaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "no_historia_clinica", nullable = false)
    private Long noHistoriaClinica;

    @Column(name = "per_apellido")
    private String primerApellido;

    @Column(name = "sdo_apellido")
    private String segundoApellido;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Ciudad")
    private String ciudad;

    @Column(name = "Ocupacion")
    private String ocupacion;

    @Column(name = "Edad")
    private String edad;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MC")
    private String mc;

    @Column(name = "HEA")
    private String hea;

    @Column(name = "APP")
    private String app;

    @Column(name = "APF")
    private String apf;

    @Column(name = "Ojo")
    private String ojo;

    @Column(name = "Mano")
    private String mano;

    @Column(name = "Directa")
    private String directa;

    @Column(name = "Inversa")
    private String inversa;

}

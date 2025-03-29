package deprecated.historiaClinica.dto;

import jakarta.validation.constraints.NotBlank;

public record HistoriaClinicaRequest(
        Long noHistoriaClinica,

//        @NotBlank(message = "El nombre es requerido")
        String nombre,
//        @NotBlank(message = "El apellido es requerido")
        String primerApellido,

        String segundoApellido,

//        @NotBlank(message = "La ciudad es requerida")
        String ciudad,

        String ocupacion,

//        @NotBlank(message = "La edad es requerida")
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

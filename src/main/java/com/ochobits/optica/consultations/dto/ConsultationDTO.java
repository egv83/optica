package com.ochobits.optica.consultations.dto;

import java.time.LocalDateTime;

public record ConsultationDTO(
        Long id,
        Long medicalRecord,
        String fecha,
        String mc,
        String cscod,
        String cscoi,
        String scod,
        String scoi,
        String usaod,
        String usaoi,
        String autorefod,
        String autorefoi,
        String usaadd,
        String dp,
        String od,
        String oi,
        String padd,
        String vi,
        String control
        ) {
}

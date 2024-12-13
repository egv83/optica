package com.ochobits.optica.historiaClinica.dto;

import java.util.*;

public record HistoriaClinicaResponse(
        List<HistoriaClinica> historiaClinica,
        //SE AGREGO OPTIONAL EN EL CASO SI NO SE VA A PASAR DATOS DE PAGE
//        Optional<PageDetails> page,
        String message // Agregamos un campo de mensaje
) {
//    public record PageDetails(
//            int pageNumber,
//            int pageSize,
//            long totalElements,
//            int totalPages
//    ){}
//
//    public static HistoriaClinicaResponse withData(
//            List<HistoriaClinica> historiaClinica,
//            PageDetails page
//    ){
//        return new HistoriaClinicaResponse(historiaClinica, Optional.of(page),null);
//    }
//
//    public static HistoriaClinicaResponse withoutPagination(
//            List<HistoriaClinica> historiaClinica
//    ){
//        return new HistoriaClinicaResponse(historiaClinica,null,null);
//    }
//
//    public static Map<String, Object> withError(
//      String message,
//      Optional<PageDetails> page
//    ){
//        Map<String, Object> errorResponse = new HashMap<>();
//        errorResponse.put("historiaClinica",List.of());
//        page.ifPresent(p-> errorResponse.put("page",p)); //si no se quiere opciinal se retira lo referente al optional
//        errorResponse.put("message",message);
//        return errorResponse;
//    }




//    public static List<HistoriaClinicaResponse> withError2(
//            String message,
//            Optional<PageDetails> page
//    ){
//        // Creamos la respuesta de error con los valores proporcionados
//        HistoriaClinicaResponse errorResponse = new HistoriaClinicaResponse(
//                List.of(),  // Lista vacía de HistoriaClinica
//                Optional.empty(),  // Si hay datos de paginación, los usamos, si no, usamos null
//                message  // El mensaje de error
//        );
//        // Retornamos una lista con la respuesta de error
//        return List.of(errorResponse);
//    }

//    // Método con error que retorna una lista de HistoriaClinicaResponse con el mensaje y la página opcional
//    public static List<HistoriaClinicaResponse> withError3(
//            String message,
//            Optional<PageDetails> page
//    ) {
//        // Reutilizamos el método withError para retornar una lista de errores
//        return withError2(message, page);
//    }

//    // Método para convertir la respuesta en un mapa para poder controlar mejor los valores nulos
//    public Map<String, Object> toMap() {
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("historiaClinica", this.historiaClinica);
//
//        // Solo incluimos el campo 'page' si no es vacío
//        if (this.page.isPresent()) {
//            responseMap.put("page", this.page.get());
//        }
//
//        // Solo incluimos 'message' si no es null
//        if (this.message != null) {
//            responseMap.put("message", this.message);
//        }
//
//        return responseMap;
//    }

}

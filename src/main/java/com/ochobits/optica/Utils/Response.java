package com.ochobits.optica.Utils;

import java.util.*;

//@JsonSerialize(using = ResponseSerializer.class)
public record Response<T>(
        List<T> data,
        Optional<PageDetails> page,
        String message
) {

    public Response(List<T> data, Optional<PageDetails> page, String message) {
        this.data = data;
        this.page = page != null ? page : Optional.empty();
        this.message = message;
    }

    public record PageDetails(
            int pageNumber,
            int pageSize,
            long totalElements,
            int totalPages
    ){}

    public static <T> Response<T> withData(
            List<T> data,
            PageDetails page
    ){
        return new Response<>(data, Optional.of(page),null);
    }

    public static <T> Response<T> withoutPagination(
            List<T> data
    ){
        return new Response<>(data,null,null);
    }


//    public static <T> Response<T> withData(
//            Map<String,Object> data,
//            PageDetails page
//    ){
//        return new Response<>(data, Optional.of(page),null);
//    }
//
//    public static <T> Response<T> withoutPagination(
//            Map<String,Object> data
//    ){
//
//        return new Response<>(data,null,null);
//    }

    public static <T> Map<String, Object> withError(
            String message,
            Optional<PageDetails> page,
            Class<T> tClass //Clase de tipo generico
    ){
        Map<String, Object> errorResponse = new HashMap<>();
        String className = tClass.getSimpleName().substring(0,1).toLowerCase() +
                tClass.getSimpleName().substring(1);

        errorResponse.put(className,List.of());
        page.ifPresent(p-> errorResponse.put("page",p)); //si no se quiere opciinal se retira lo referente al optional
        errorResponse.put("message",message);
        return errorResponse;
    }

    /**
     * Genera un mapa con el nombre dinámico del campo.
     */
    public Map<String, Object> toDynamicMap(Class<T> fieldName) {
        Map<String, Object> responseMap = new LinkedHashMap<>();
        String fieldKey = fieldName.getSimpleName();
        responseMap.put(fieldKey.substring(0, 1) + fieldKey.substring(1), this.data);
        page.ifPresent(p -> responseMap.put("page", p));
        if (message != null) {
            responseMap.put("message", message);
        }
        return responseMap;
    }

}

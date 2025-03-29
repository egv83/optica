package com.ochobits.optica.Utils.response;

import com.ochobits.optica.Utils.PageDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class ResponseUtil {

    public static <T> ResponseEntity<Map<String, Object>> buildResponse(ResponseObject<T> responseObject, HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        if (Objects.nonNull(responseObject.getData())) {
            String key = responseObject.getData().getClass().getSimpleName();
            response.put(key, responseObject.getData());
        }

        if (Objects.nonNull(responseObject.getPage())) {
            response.put("pagination", responseObject.getPage());
        }

        if (Objects.nonNull(responseObject.getMessage())) {
            response.put("message", responseObject.getMessage());
        }else {
            response.put("message",httpStatus.isError() ? "Ocurrio un error" : "Operation Exitosa");
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static <T> ResponseEntity<Map<String, Object>> buildResponse(T data, PageDetails page, String message, HttpStatus httpStatus) {
        Map<String, Object> response = new LinkedHashMap<>();
        if (Objects.nonNull(data)) {
            String key = data.getClass().getSimpleName();
            response.put(key, data);
        }

        if (Objects.nonNull(page)) {
            response.put("pagination", page);
        }

        if (Objects.nonNull(message)) {
            response.put("message", message);
        }else {
            response.put("message",httpStatus.isError() ? "Ocurrio un error" : "Operation Exitosa");
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static <T> ResponseEntity<Map<String, Object>> buildResponse(List<T> data, Class<T> clazz, PageDetails page, String message, HttpStatus httpStatus) {
        Map<String, Object> response = new LinkedHashMap<>();
        if (Objects.nonNull(data)) {
            String key = clazz.getSimpleName();
            response.put(key, data);
        }

        if (Objects.nonNull(page)) {
            response.put("pagination", page);
        }

        if (Objects.nonNull(message)) {
            response.put("message", message);
        }else {
            response.put("message",httpStatus.isError() ? "Ocurrio un error" : "Operation Exitosa");
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

}

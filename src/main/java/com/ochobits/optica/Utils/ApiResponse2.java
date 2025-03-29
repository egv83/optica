package com.ochobits.optica.Utils;

import java.util.HashMap;
import java.util.Map;

public record ApiResponse2(Map<String,Object> data, String message) {

    public ApiResponse2(Object data, String message){
        this(createMapWithDynamicKey(data),message);
    }

    private static Map<String,Object> createMapWithDynamicKey(Object data){
        Map<String, Object> responseMap = new HashMap<>();
        String className = data.getClass().getSimpleName();
        responseMap.put(className,data);
        return responseMap;
    }

}

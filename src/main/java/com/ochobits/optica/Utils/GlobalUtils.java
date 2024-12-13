package com.ochobits.optica.Utils;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class GlobalUtils {

    public static <T> Long getNewId(JpaRepository<T,?> repository){
        return repository.count()+1;
    }

    public static Map<String, Object> toMap(Object obj) {
        Map<String, Object> map = new HashMap<>();

        // Si el objeto es nulo, retornamos un mapa vacío
        if (obj == null) {
            return map;
        }

        // Obtenemos todos los campos de la clase del objeto
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true); // Permitir acceso a campos privados
                Object value = field.get(obj); // Obtenemos el valor del campo

                // Solo incluimos el campo si su valor no es null
                if (value != null) {
                    map.put(field.getName(), value);
                }
            } catch (IllegalAccessException e) {
                // Manejo de excepciones en caso de que no podamos acceder al campo
                e.printStackTrace();
            }
        }

        return map;
    }

}

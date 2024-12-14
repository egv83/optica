package com.ochobits.optica.Utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ResponseSerializer<T> extends JsonSerializer<Response<T>> {

    @Override
    public void serialize(Response<T> response, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//        gen.writeStartObject();
//
//        // Cambiar "data" al nombre de la clase contenida en la lista
//        if (!response.data().isEmpty()) {
//            String fieldName = response.data().get(0).getClass().getSimpleName().toLowerCase();
//            gen.writeArrayFieldStart(fieldName);
//            for (T item : response.data()) {
//                gen.writeObject(item);
//            }
//            gen.writeEndArray();
//        }
//
//        // Serializar el objeto page si está presente
//        if (response.page().isPresent()) {
//            gen.writeObjectField("page", response.page().get());
//        }
//
//        // Serializar el mensaje si existe
//        if (response.message() != null) {
//            gen.writeStringField("message", response.message());
//        }
//
//        gen.writeEndObject();
    }

}

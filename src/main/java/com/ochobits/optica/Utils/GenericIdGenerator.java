package com.ochobits.optica.Utils;

import org.springframework.data.jpa.repository.JpaRepository;

public class GenericIdGenerator {

    public static <T> Long getNewId(JpaRepository<T,?> repository){
        return repository.count()+1;
    }

}

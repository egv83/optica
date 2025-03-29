package com.ochobits.optica.Utils;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public class GenericIdGenerator {

    public static <T> Long getNewId(JpaRepository<T,?> repository){
        return repository.count()+1;
    }

//    public static <T> Specification<T> getMaxId(){
//        return (root, query, criteriaBuilder) ->
//        {
//            query.select(criteriaBuilder.max(root.get("id")));
//            return null;
//        };
//    }

}

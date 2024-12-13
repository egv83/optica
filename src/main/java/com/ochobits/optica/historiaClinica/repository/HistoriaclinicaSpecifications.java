package com.ochobits.optica.historiaClinica.repository;

import com.ochobits.optica.historiaClinica.entities.HistoriaclinicaEntity;
import org.springframework.data.jpa.domain.Specification;

public class HistoriaclinicaSpecifications {

    public static Specification<HistoriaclinicaEntity> searchByFullName(String fullName){
        return (root, query, criteriaBuilder) ->
        {
            String pattern = "%"+fullName+"%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("nombre"),pattern),
                    criteriaBuilder.like(root.get("primerApellido"),pattern),
                    criteriaBuilder.like(root.get("segundoApellido"),pattern),
                    criteriaBuilder.like(
                            criteriaBuilder.concat(
                                criteriaBuilder.concat(
                                        criteriaBuilder.concat(root.get("nombre")," "),
                                        criteriaBuilder.concat(root.get("primerApellido")," ")
                                ),root.get("segundoApellido")
                            ),pattern
                    )
            );
        };

    }

}

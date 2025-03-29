package deprecated.historiaClinica.repository;

import com.ochobits.optica.medicalRecord.model.MedicalRecordEntity;
import org.springframework.data.jpa.domain.Specification;

public class BuscarHistoriaClinicaSpecifications {

    public static Specification<MedicalRecordEntity> searchByFullName(String fullName){
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

//    public static Specification<HistoriaClinicaEntity> searchByFullName2(String fullName) {
//        return (root, query, criteriaBuilder) -> {
//            // Divide el nombre completo en palabras
//            String[] tokens = fullName.trim().split("\\s+");
//
//            // Lista para almacenar las condiciones dinámicas
//            List<Predicate> predicates = new ArrayList<>();
//
//            // Genera combinaciones dinámicas para las condiciones de búsqueda
//            for (String token : tokens) {
//                String pattern = "%" + token + "%";
//                predicates.add(criteriaBuilder.like(root.get("nombre"), pattern));
//                predicates.add(criteriaBuilder.like(root.get("primerApellido"), pattern));
//                predicates.add(criteriaBuilder.like(root.get("segundoApellido"), pattern));
//            }
//
//            // Construye combinaciones completas del nombre
//            if (tokens.length > 1) {
//                predicates.add(
//                        criteriaBuilder.like(
//                                criteriaBuilder.concat(
//                                        criteriaBuilder.concat(
//                                                criteriaBuilder.concat(root.get("nombre"), " "),
//                                                criteriaBuilder.concat(root.get("primerApellido"), " ")
//                                        ),
//                                        root.get("segundoApellido")
//                                ),
//                                "%" + fullName + "%"
//                        )
//                );
//            }
//
//            // Retorna la combinación de todas las condiciones
//            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
//        };
//    }
//
//    public static Specification<HistoriaClinicaEntity> searchByFullName3(String fullName) {
//        return (root, query, criteriaBuilder) -> {
//            // Dividir el texto de búsqueda en palabras
//            String[] palabras = fullName.trim().split("\\s+");
//
//            // Crear patrones con % para búsqueda parcial
//            List<Predicate> predicates = new ArrayList<>();
//
//            for (String palabra : palabras) {
//                String pattern = "%" + palabra + "%";
//
//                // Añadir predicados para cada palabra
//                predicates.add(criteriaBuilder.like(root.get("nombre"), pattern));
//                predicates.add(criteriaBuilder.like(root.get("primerApellido"), pattern));
//                predicates.add(criteriaBuilder.like(root.get("segundoApellido"), pattern));
//            }
//
//            // Concatenar los campos para búsquedas combinadas
//            Expression<String> nombreCompleto = criteriaBuilder.concat(
//                    criteriaBuilder.concat(
//                            criteriaBuilder.concat(root.get("nombre"), " "),
//                            criteriaBuilder.concat(root.get("primerApellido"), " ")
//                    ),
//                    root.get("segundoApellido")
//            );
//
//            // Agregar búsqueda por combinaciones completas
//            String fullPattern = "%" + fullName.replaceAll("\\s+", "%") + "%";
//            predicates.add(criteriaBuilder.like(nombreCompleto, fullPattern));
//
//            // Combinar todos los predicados usando OR
//            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
//        };
//    }
//
//    public static Specification<HistoriaClinicaEntity> searchByFullName4(String fullName) {
//        return (root, query, criteriaBuilder) -> {
//            // Dividir el texto de búsqueda en palabras
//            String[] palabras = fullName.trim().split("\\s+");
//
//            // Caso 1: Comparación exacta del nombre completo
//            Expression<String> nombreCompleto = criteriaBuilder.concat(
//                    criteriaBuilder.concat(
//                            criteriaBuilder.concat(root.get("nombre"), " "),
//                            criteriaBuilder.concat(root.get("primerApellido"), " ")
//                    ),
//                    root.get("segundoApellido")
//            );
//            Predicate exactMatch = criteriaBuilder.like(nombreCompleto, "%" + fullName.trim() + "%");
//
//            // Caso 2: Coincidencia parcial por cada palabra
//            List<Predicate> partialPredicates = new ArrayList<>();
//            for (String palabra : palabras) {
//                String pattern = "%" + palabra + "%";
//                partialPredicates.add(criteriaBuilder.like(root.get("nombre"), pattern));
//                partialPredicates.add(criteriaBuilder.like(root.get("primerApellido"), pattern));
//                partialPredicates.add(criteriaBuilder.like(root.get("segundoApellido"), pattern));
//            }
//
//            // Combinar búsquedas: Priorizar coincidencia exacta, luego parciales
//            Predicate partialMatch = criteriaBuilder.or(partialPredicates.toArray(new Predicate[0]));
//
//            return criteriaBuilder.or(
//                    exactMatch, // Prioridad para coincidencia exacta
//                    partialMatch // Luego buscar coincidencias parciales
//            );
//        };
//    }

}


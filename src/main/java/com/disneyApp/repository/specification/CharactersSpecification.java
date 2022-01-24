package com.disneyApp.repository.specification;

import com.disneyApp.DTO.filters.CharactersFiltersDto;
import com.disneyApp.entity.Characters;
import com.disneyApp.entity.Films;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


public class CharactersSpecification {
    public Specification<Characters> getFiltered(CharactersFiltersDto charactersFilters){

        // LAMBDA Function:
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // == Name ==
            // IF hay algo en la String:
            // 	predicates.add(
            //		Construimos un SQL LIKE -> (Database Table, a Comparar) --> ejemplo: (name, valorDto)
            if(StringUtils.hasLength(charactersFilters.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + charactersFilters.getName().toLowerCase() + "%"
                        )
                );
            }

            // == Age ==
            // Casteo a string
            // IF Hay algo -> Comparar "age" con el INT pasado en Dto.
            if(charactersFilters.getAge() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), charactersFilters.getAge())								);
            }

            // == CharFilms
            // IF Not Empty
            //	JOIN ("characters" type INNER) con filmsID ("id"). Una vez unido Agregamos JOIN = LO QUE SE AGREGA
            // 	ADD -> los filmsId iguales al DTO enviado, dentro de mi tabla conjunta.
            // En Criollo:
            //		Pegamos las tablas (Characters y Films) (Hibernate se encarga de encontrarlas
            //								a partir de las entidades)
            //		Tomamos el ID de films, para cada una de las relaciones existentes, y lo guardamos (filmsID)
            //		Add -> si dicho filmsID, coincide con el del Dto Filtrado.
            if(!CollectionUtils.isEmpty(charactersFilters.getFilms())) {
                Join<Characters, Films> join = root.join("charactersFilms", JoinType.INNER);
                Expression<String> filmsId = join.get("id");
                predicates.add(filmsId.in(charactersFilters.getFilms()));
            }

            // Removemos Duplicados:
            query.distinct(true);
            // Damos un orden HARD CODEADO: ASC por NAME
            query.orderBy(criteriaBuilder.asc(root.get("name")));

            // MAIN RETURN:
            return criteriaBuilder.and( predicates.toArray(new Predicate[0]));

        };
    }
}

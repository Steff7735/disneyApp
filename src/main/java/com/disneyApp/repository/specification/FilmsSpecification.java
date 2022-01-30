package com.disneyApp.repository.specification;

import com.disneyApp.Dto.filters.FilmsFiltersDto;
import com.disneyApp.entity.Films;
import com.disneyApp.entity.Genders;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Component
public class FilmsSpecification {
    public Specification<Films> getFiltered(FilmsFiltersDto filmsFiltersDto){

        // Lambda Function:
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // == Name ==
            if(StringUtils.hasLength(filmsFiltersDto.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filmsFiltersDto.getTitle().toLowerCase() + "%"
                        )
                );
            }


            if(!CollectionUtils.isEmpty(filmsFiltersDto.getGenders())) {
                Join<Films, Genders> join = root.join("genders", JoinType.INNER);
                Expression<String> gendersId = join.get("id");
                predicates.add(gendersId.in(filmsFiltersDto.getGenders()));
            }

            query.distinct(true);


            String orderByField = "title";
            query.orderBy(
                    filmsFiltersDto.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );



            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

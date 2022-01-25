package com.disneyApp.mapper;

import com.disneyApp.DTO.FilmsDto;
import com.disneyApp.entity.Films;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmsMapper {

    public Films filmsDto2Entity(FilmsDto filmsDto) {
        Films films = new Films();
        films.setTitle(filmsDto.getTitle());
        films.setImage(filmsDto.getImage());
        films.setDateCreation(filmsDto.getDateCreation());
        films.setStars(filmsDto.getStars());
        return films;

    }

    public FilmsDto films2Dto(Films films, boolean b) {
        FilmsDto filmsDto = new FilmsDto();
        filmsDto.setId(films.getId());
        filmsDto.setTitle(films.getTitle());
        filmsDto.setImage(films.getImage());
        filmsDto.setDateCreation(films.getDateCreation());
        filmsDto.setStars(films.getStars());
        return filmsDto;
    }

    public List<FilmsDto> filmsList2DtoList(List<Films> list, boolean b) {
        List<FilmsDto> dtoList = new ArrayList<>();
        for (Films films : list) {
            dtoList.add(this.films2Dto(films, b));
        }
        return dtoList;
    }

}

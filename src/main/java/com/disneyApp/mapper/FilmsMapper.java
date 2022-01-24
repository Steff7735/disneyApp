package com.disneyApp.mapper;


import com.disneyApp.DTO.FilmsDto;
import com.disneyApp.entity.Films;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilmsMapper {

    @Autowired
    private CharactersMapper charactersMapper;
    @Autowired
    private GendersMapper gendersMapper;



    public Films filmsDto2Entity(FilmsDto filmsDto){
        Films films = new Films();
        films.setTitle(filmsDto.getTitle());
        films.setImage(filmsDto.getImage());
        films.setDateCreation(filmsDto.getDateCreation());
        films.setStars(filmsDto.getStars());
        return films;

    }

    public FilmsDto films2Dto(Films savedFilms, boolean b){
        FilmsDto filmsDto = new FilmsDto();
        filmsDto.setId(savedFilms.getId());
        filmsDto.setTitle(savedFilms.getTitle());
        filmsDto.setImage(savedFilms.getImage());
        filmsDto.setDateCreation(savedFilms.getDateCreation());
        filmsDto.setStars(savedFilms.getStars());
        return filmsDto;
    }

    public List<FilmsDto> filmsList2DtoList(List<Films> filmsList, boolean b){
        List<FilmsDto> dtoList = new ArrayList<>();
        for(Films ent : filmsList) {
            dtoList.add(this.films2Dto(ent, b));
        }
        return dtoList;
    }


}

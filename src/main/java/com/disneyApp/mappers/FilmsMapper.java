package com.disneyApp.mappers;


import com.disneyApp.DTO.FilmsDto;
import com.disneyApp.entity.FilmsEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilmsMapper {

    public FilmsEntity filmsDto2Entity(FilmsDto dto){
        FilmsEntity filmsEntity = new FilmsEntity();
        filmsEntity.setTitle(dto.getTitle());
        filmsEntity.setImage(dto.getImage());
        filmsEntity.setDateCreation(dto.getDateCreation());
        filmsEntity.setStars(dto.getStars());
        filmsEntity.setCharactersAsoc(dto.getCharactersAsoc());
        return filmsEntity;

    }

    public FilmsDto filmsEntity2Dto(FilmsEntity entity){
        FilmsDto filmsDto = new FilmsDto();
        filmsDto.setId(entity.getId());
        filmsDto.setTitle(entity.getTitle());
        filmsDto.setImage(entity.getImage());
        filmsDto.setDateCreation(entity.getDateCreation());
        filmsDto.setStars(entity.getStars());
        filmsDto.setCharactersAsoc(entity.getCharactersAsoc());
        return filmsDto;
    }

    public List<FilmsDto> filmsEntityList2DtoList(List<FilmsEntity> entities){
        List<FilmsDto> dtoList = new ArrayList<>();

        for (FilmsEntity entity: entities) {
            dtoList.add(this.filmsEntity2Dto(entity));

        }
        return dtoList;
    }
}

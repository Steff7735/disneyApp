package com.disneyApp.mapper;

import com.disneyApp.DTO.GendersDto;
import com.disneyApp.entity.Genders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GendersMapper {

    @Autowired
    private FilmsMapper filmsMapper;

    public Genders gendersDto2Entity(GendersDto newGenders){
        Genders genders = new Genders();
        genders.setNombre(newGenders.getNombre());
        genders.setImagen(newGenders.getImagen());
        genders.setFilmsAsoc(newGenders.getFilmsAsoc());
        return genders;

    }

    public GendersDto genders2Dto(Genders savedGenders, boolean b){
        GendersDto gendersDto = new GendersDto();
        gendersDto.setId(savedGenders.getId());
        gendersDto.setNombre(savedGenders.getNombre());
        gendersDto.setImagen(savedGenders.getImagen());
        gendersDto.setFilmsAsoc(savedGenders.getFilmsAsoc());
        return gendersDto;
    }

    public List<GendersDto> gendersList2DtoList(List<Genders> savedGenders, boolean b){
        List<GendersDto> dtoList = new ArrayList<>();

        for (Genders genders : savedGenders) {
            dtoList.add(this.genders2Dto(genders, false));

        }
        return dtoList;
    }
}

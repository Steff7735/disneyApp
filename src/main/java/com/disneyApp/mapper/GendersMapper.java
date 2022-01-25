package com.disneyApp.mapper;

import com.disneyApp.DTO.GendersDto;
import com.disneyApp.entity.Genders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GendersMapper {

    public Genders gendersDto2Entity(GendersDto gendersDto) {
        Genders genders = new Genders();
        genders.setNombre(gendersDto.getNombre());
        genders.setImagen(gendersDto.getImagen());
        genders.setFilmsAsoc(gendersDto.getFilmsAsoc());
        return genders;

    }

    public GendersDto genders2Dto(Genders genders, boolean b) {
        GendersDto gendersDto = new GendersDto();
        gendersDto.setId(genders.getId());
        gendersDto.setNombre(genders.getNombre());
        gendersDto.setImagen(genders.getImagen());
        gendersDto.setFilmsAsoc(genders.getFilmsAsoc());
        return gendersDto;
    }

    public List<GendersDto> gendersList2DtoList(List<Genders> list, boolean b) {
        List<GendersDto> dtoList = new ArrayList<>();

        for (Genders genders : list) {
            dtoList.add(this.genders2Dto(genders, false));

        }
        return dtoList;
    }
}

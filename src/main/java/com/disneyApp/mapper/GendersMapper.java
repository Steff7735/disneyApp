package com.disneyApp.mapper;

import com.disneyApp.Dto.GendersDto;
import com.disneyApp.entity.Genders;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GendersMapper {

    public Genders gendersDto2Entity(GendersDto gendersDto) {
        Genders genders = new Genders();
        genders.setName(gendersDto.getName());
        genders.setImage(gendersDto.getImage());
        genders.setFilmsA(gendersDto.getFilmsA());
        return genders;

    }

    public GendersDto genders2Dto(Genders genders, boolean b) {
        GendersDto gendersDto = new GendersDto();
        gendersDto.setId(genders.getId());
        gendersDto.setName(genders.getName());
        gendersDto.setImage(genders.getImage());
        gendersDto.setFilmsA(genders.getFilmsA());
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

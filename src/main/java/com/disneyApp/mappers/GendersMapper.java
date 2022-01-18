package com.disneyApp.mappers;

import com.disneyApp.DTO.GendersDto;
import com.disneyApp.entity.GendersEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GendersMapper {

    public GendersEntity gendersDto2Entity(GendersDto dto){
        GendersEntity gendersEntity = new GendersEntity();
        gendersEntity.setNombre(dto.getNombre());
        gendersEntity.setImagen(dto.getImagen());
        gendersEntity.setFilmsAsoc(dto.getFilmsAsoc());
        return gendersEntity;

    }

    public GendersDto gendersEntity2Dto(GendersEntity entity){
        GendersDto gendersDto = new GendersDto();
        gendersDto.setId(entity.getId());
        gendersDto.setNombre(entity.getNombre());
        gendersDto.setImagen(entity.getImagen());
        gendersDto.setFilmsAsoc(entity.getFilmsAsoc());
        return gendersDto;
    }

    public List<GendersDto> gendersEntityList2DtoList(List<GendersEntity> entities){
        List<GendersDto> dtoList = new ArrayList<>();

        for (GendersEntity entity: entities) {
            dtoList.add(this.gendersEntity2Dto(entity));

        }
        return dtoList;
    }
}

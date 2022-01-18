package com.disneyApp.mappers;

import com.disneyApp.DTO.CharactersDto;
import com.disneyApp.entity.CharactersEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharactersMapper {

    public CharactersEntity charactersDto2Entity(CharactersDto dto){
        CharactersEntity charactersEntity = new CharactersEntity();
        charactersEntity.setName(dto.getName());
        charactersEntity.setAge(dto.getAge());
        charactersEntity.setWeight(dto.getWeight());
        charactersEntity.setHistory(dto.getHistory());
        charactersEntity.setFilmsAsoc(dto.getFilmsAsoc());
        return charactersEntity;

    }

    public CharactersDto charactersEntity2Dto(CharactersEntity entity){
        CharactersDto charactersDto = new CharactersDto();
        charactersDto.setId(entity.getId());
        charactersDto.setName(entity.getName());
        charactersDto.setAge(entity.getAge());
        charactersDto.setWeight(entity.getWeight());
        charactersDto.setHistory(entity.getHistory());
        charactersDto.setFilmsAsoc(entity.getFilmsAsoc());
        return charactersDto;
    }

    public List<CharactersDto> charactersEntityList2DtoList(List<CharactersEntity> entities){
        List<CharactersDto> dtoList = new ArrayList<>();

        for (CharactersEntity entity: entities) {
            dtoList.add(this.charactersEntity2Dto(entity));

        }
        return dtoList;
    }
}

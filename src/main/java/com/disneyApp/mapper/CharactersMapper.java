package com.disneyApp.mapper;

import com.disneyApp.Dto.CharactersDto;
import com.disneyApp.entity.Characters;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharactersMapper {

    public Characters charactersDto2Entity(CharactersDto charactersDto) {
        Characters characters = new Characters();
        characters.setName(charactersDto.getName());
        characters.setAge(charactersDto.getAge());
        characters.setWeight(charactersDto.getWeight());
        characters.setHistory(charactersDto.getHistory());
        characters.setFilmsA(charactersDto.getFilmsA());
        return characters;

    }

    public CharactersDto characters2Dto(Characters characters, boolean b) {
        CharactersDto charactersDto = new CharactersDto();
        charactersDto.setId(characters.getId());
        charactersDto.setName(characters.getName());
        charactersDto.setAge(characters.getAge());
        charactersDto.setWeight(characters.getWeight());
        charactersDto.setHistory(characters.getHistory());
        charactersDto.setFilmsA(characters.getFilmsA());
        return charactersDto;
    }

    public List<CharactersDto> charactersList2DtoList(List<Characters> list, boolean b) {
        List<CharactersDto> dtoList = new ArrayList<>();

        for (Characters characters : list) {
            dtoList.add(this.characters2Dto(characters, true));
            dtoList.add(this.characters2Dto(characters, b));

        }
        return dtoList;
    }
}

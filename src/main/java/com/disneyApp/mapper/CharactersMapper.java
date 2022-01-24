package com.disneyApp.mapper;

import com.disneyApp.DTO.CharactersDto;
import com.disneyApp.entity.Characters;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CharactersMapper {
    
    @Autowired
    private FilmsMapper filmsMapper;


    public Characters charactersDto2Entity(CharactersDto charactersDto){
        Characters newCharacters = new Characters();
        newCharacters.setName(charactersDto.getName());
        newCharacters.setAge(charactersDto.getAge());
        newCharacters.setWeight(charactersDto.getWeight());
        newCharacters.setHistory(charactersDto.getHistory());
        newCharacters.setFilmsAsoc(charactersDto.getFilmsAsoc());
        return newCharacters;

    }

    public CharactersDto characters2Dto(Characters savedCharacters, boolean b){
        CharactersDto charactersDto = new CharactersDto();
        charactersDto.setId(savedCharacters.getId());
        charactersDto.setName(savedCharacters.getName());
        charactersDto.setAge(savedCharacters.getAge());
        charactersDto.setWeight(savedCharacters.getWeight());
        charactersDto.setHistory(savedCharacters.getHistory());
        charactersDto.setFilmsAsoc(savedCharacters.getFilmsAsoc());
        return charactersDto;
    }

    public List<CharactersDto> charactersList2DtoList(List<Characters> characters, boolean b){
        List<CharactersDto> newList = new ArrayList<>();

        for (Characters entity: characters) {
            newList.add(this.characters2Dto(entity, true));
            newList.add(this.characters2Dto(entity, b));

        }
        return newList;
    }
}

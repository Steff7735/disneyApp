package com.disneyApp.service;

import com.disneyApp.DTO.CharactersDto;

import java.util.List;

public interface CharactersService {

    CharactersDto save(CharactersDto newCharacters);
    
    List<CharactersDto> getAllCharacters();
    
    CharactersDto getCharactersDetails(String id);

    CharactersDto editCharactersById(String id, CharactersDto charactersToEdit);

    void deleteCharactersById(String id);

    List<CharactersDto> getByFilters(String name, Integer age, List<String> films);
}

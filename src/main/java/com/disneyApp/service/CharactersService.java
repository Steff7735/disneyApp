package com.disneyApp.service;

import com.disneyApp.Dto.CharactersDto;

import java.util.List;

public interface CharactersService {

    CharactersDto save(CharactersDto charactersDto);
    
    List<CharactersDto> getAll();
    
    CharactersDto getDetails(String charactersId);

    CharactersDto editById(String charactersId, CharactersDto charactersDto);

    void deleteById(String charactersId);

    List<CharactersDto> getByFilters(String name, Integer age, List<String> films);
}

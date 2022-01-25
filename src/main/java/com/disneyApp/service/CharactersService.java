package com.disneyApp.service;

import com.disneyApp.DTO.CharactersDto;

import java.util.List;

public interface CharactersService {

    CharactersDto save(CharactersDto charactersDto);
    
    List<CharactersDto> getAll();
    
    CharactersDto getDetails(String id);

    CharactersDto editById(String id, CharactersDto charactersDto);

    void deleteById(String id);

    List<CharactersDto> getByFilters(String name, Integer age, List<String> films);
}

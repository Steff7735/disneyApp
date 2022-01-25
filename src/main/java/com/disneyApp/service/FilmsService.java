package com.disneyApp.service;

import com.disneyApp.DTO.FilmsDto;
import com.disneyApp.entity.Films;
import com.disneyApp.entity.Genders;

import java.util.List;

public interface FilmsService {

    FilmsDto save(FilmsDto filmsDto);

    void addCharacters(String id, String charactersId);

    void addGenders(String id, String gendersId);

    List<FilmsDto> getAll();

    FilmsDto getDetails(String id);

    void deleteById(String id);

    FilmsDto editById(String id, FilmsDto filmsToEdit);

    List<FilmsDto> getByFilters(String title, List<String> genders, Integer order);
    
    Films handleById(String id);

}

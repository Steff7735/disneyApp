package com.disneyApp.service;

import com.disneyApp.Dto.FilmsDto;
import com.disneyApp.entity.Films;

import java.util.List;

public interface FilmsService {

    FilmsDto save(FilmsDto filmsDto);

    void addCharacters(String filmsId, String charactersId);

    void addGenders(String filmsId, String gendersId);

    List<FilmsDto> getAll();

    FilmsDto getDetails(String filmsId);

    void deleteById(String filmsId);

    FilmsDto editById(String filmsId, FilmsDto filmsToEdit);

    List<FilmsDto> getByFilters(String title, List<String> genders, Integer order);
    
    Films handleById(String filmsId);

}

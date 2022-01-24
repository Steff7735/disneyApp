package com.disneyApp.service;

import com.disneyApp.DTO.FilmsDto;

import java.util.List;

public interface FilmsService {

    FilmsDto savedNewFilms(FilmsDto newFilms);

    void addCharactersToFilms(String id, String charactersId);

    void addGendersToFilms(String id, String gendersId);

    List<FilmsDto> getAllFilms();

    FilmsDto getFilmsDetails(String id);

    void deleteFilmsById(String id);

    FilmsDto editFilmsById(String id, FilmsDto filmsToEdit);

    List<FilmsDto> getByFilters(String name, List<String> genders, Integer order);

}

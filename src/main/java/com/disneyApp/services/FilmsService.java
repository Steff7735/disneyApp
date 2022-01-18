package com.disneyApp.services;

import com.disneyApp.DTO.FilmsDto;

import java.util.List;

public interface FilmsService {

    FilmsDto save(FilmsDto dto);
    List<FilmsDto> getAllFilms();
}

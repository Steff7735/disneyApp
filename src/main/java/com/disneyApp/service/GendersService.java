package com.disneyApp.service;

import com.disneyApp.DTO.GendersDto;
import com.disneyApp.entity.Genders;

import java.util.List;

public interface GendersService {

    GendersDto save(GendersDto gendersDto);

    List<GendersDto> getAll();

    void deleteById(String id);

    GendersDto editById(String id, GendersDto gendersDto);

    Genders handleById(String id);
}

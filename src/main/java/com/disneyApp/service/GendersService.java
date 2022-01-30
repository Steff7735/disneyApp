package com.disneyApp.service;

import com.disneyApp.Dto.GendersDto;
import com.disneyApp.entity.Genders;

import java.util.List;

public interface GendersService {

    GendersDto save(GendersDto gendersDto);

    List<GendersDto> getAll();

    void deleteById(String gendersId);

    GendersDto editById(String gendersId, GendersDto gendersDto);

    Genders handleById(String gendersId);
}

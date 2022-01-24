package com.disneyApp.service;

import com.disneyApp.DTO.GendersDto;
import com.disneyApp.entity.Genders;

import java.util.List;

public interface GendersService {

    GendersDto savedNewGenders(GendersDto newGenders);

    List<GendersDto> getAllGenders();

    void deletedGendersById(String id);

    GendersDto editGendersById(String id, GendersDto gendersToEdit);

    Genders handleFindById(String id);
}

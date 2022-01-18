package com.disneyApp.services;

import com.disneyApp.DTO.GendersDto;

import java.util.List;

public interface GendersService {

    GendersDto save(GendersDto dto);
    List<GendersDto> getAllGenders();
}

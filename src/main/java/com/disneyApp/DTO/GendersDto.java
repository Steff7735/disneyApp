package com.disneyApp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GendersDto {

    private String id;

    private String nombre;

    private String imagen;

    private String filmsAsoc;

    private List<FilmsDto> genders;
}

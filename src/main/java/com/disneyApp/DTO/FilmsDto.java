package com.disneyApp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class FilmsDto {

    private String id;

    private String image;

    private String title;

    private LocalDate dateCreation;

    private Integer stars;

    private List<GendersDto> genders ;

    private List<CharactersDto> characters;
}

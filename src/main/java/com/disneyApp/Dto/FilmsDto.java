package com.disneyApp.Dto;

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

    private List<CharactersDto> charactersDto ;

    private List<GendersDto> gendersDto;
}

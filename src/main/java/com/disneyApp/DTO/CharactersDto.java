package com.disneyApp.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CharactersDto {

    private String id;

    private String name;

    private String age;

    private Integer weight;

    private String history;

    private String filmsAsoc;

    private List<FilmsDto> characters;
}

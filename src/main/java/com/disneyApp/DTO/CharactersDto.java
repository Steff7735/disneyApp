package com.disneyApp.DTO;

import com.disneyApp.entity.Films;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

    private boolean deleted = Boolean.FALSE;

    private List<Films> charactersFilms = new ArrayList<>();
}

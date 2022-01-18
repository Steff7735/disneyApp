package com.disneyApp.DTO;

import com.disneyApp.entity.CharactersEntity;
import com.disneyApp.entity.GendersEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FilmsDto {

    private String id;

    private String image;

    private String title;

    private LocalDate dateCreation;

    private Integer stars;

    private String charactersAsoc;

    private List<GendersEntity> genders = new ArrayList<>();

    private List<CharactersEntity> characters = new ArrayList<>();
}

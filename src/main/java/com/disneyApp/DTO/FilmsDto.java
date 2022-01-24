package com.disneyApp.DTO;

import com.disneyApp.entity.Characters;
import com.disneyApp.entity.Genders;
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

    private List<Genders> genders = new ArrayList<>();

    private List<Characters> characters = new ArrayList<>();
}

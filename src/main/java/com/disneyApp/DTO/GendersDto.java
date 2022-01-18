package com.disneyApp.DTO;

import com.disneyApp.entity.FilmsEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GendersDto {

    private String id;

    private String nombre;

    private String imagen;

    private String filmsAsoc;

    private List<FilmsEntity> films = new ArrayList<>();
}

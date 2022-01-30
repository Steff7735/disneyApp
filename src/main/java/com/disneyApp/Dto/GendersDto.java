package com.disneyApp.Dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class GendersDto {

        private String id;

        private String name;

        private String image;

        private String filmsA;

        private List<FilmsDto> filmsDto;
    }


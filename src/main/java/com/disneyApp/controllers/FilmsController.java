package com.disneyApp.controllers;

import com.disneyApp.DTO.FilmsDto;
import com.disneyApp.services.FilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("films")
public class FilmsController {

    @Autowired
    private FilmsService filmsService;

    @GetMapping
    public ResponseEntity<List<FilmsDto>> getAll(){
        List<FilmsDto> films = this.filmsService.getAllFilms();
        return ResponseEntity.ok().body(films);
    }


    @PostMapping
    public ResponseEntity<FilmsDto> save(@RequestBody FilmsDto films){
        FilmsDto filmsSaved = filmsService.save(films);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmsSaved);

    }
}

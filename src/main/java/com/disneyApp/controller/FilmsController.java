package com.disneyApp.controller;

import com.disneyApp.DTO.FilmsDto;
import com.disneyApp.service.FilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("films")
public class FilmsController {

    @Autowired
    private FilmsService filmsService;

    @GetMapping
    public ResponseEntity<List<FilmsDto>> getAll(){
        List<FilmsDto> films = filmsService.getAll();
        return ResponseEntity.ok().body(films);
    }

    @GetMapping("/films/{id}")
    public ResponseEntity<FilmsDto> getFilmsById(@PathVariable String id){
        FilmsDto films = filmsService.getDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(films);
    }


    @PostMapping
    public ResponseEntity<FilmsDto> save(@RequestBody FilmsDto filmsDto){
        FilmsDto filmsSaved = filmsService.save(filmsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmsSaved);

    }

    @PostMapping("/{id}/characters/{charactersId}")
    public ResponseEntity<Void> addCharacters(@PathVariable String id, @PathVariable String charactersId){
        filmsService.addCharacters(id, charactersId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}/addGenders/{gendersId}")
    public ResponseEntity<Void> addGenders(@PathVariable String id, @PathVariable String gendersId){
        filmsService.addGenders(id, gendersId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

 
    @PutMapping("/{id}")
    public ResponseEntity<FilmsDto> editFilms(@PathVariable String id, @RequestBody FilmsDto filmsDto){
        FilmsDto editedFilms = filmsService.editById(id, filmsDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedFilms);
    }

  
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        filmsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

  
    @GetMapping
    public ResponseEntity<List<FilmsDto>> getByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) List<String> genders,
            @RequestParam(required = false, defaultValue = "ASC") Integer order
    ){
        List<FilmsDto> filteredFilms = filmsService.getByFilters(title, genders, order);
        return ResponseEntity.status(HttpStatus.OK).body(filteredFilms);
    }
}

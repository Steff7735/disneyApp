package com.disneyApp.controller;

import com.disneyApp.Dto.FilmsDto;
import com.disneyApp.service.FilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("films")
@RestController
public class FilmsController {

    @Autowired
    private FilmsService filmsService;

    @GetMapping
    public ResponseEntity<List<FilmsDto>> getAll(){
        List<FilmsDto> filmsDto = filmsService.getAll();
        return ResponseEntity.ok().body(filmsDto);
    }

    @GetMapping("/films/{id}")
    public ResponseEntity<FilmsDto> getFilmsById(@PathVariable String id){
        FilmsDto filmsDto = filmsService.getDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(filmsDto);
    }


    @PostMapping
    public ResponseEntity<FilmsDto> save(@RequestBody FilmsDto filmsDto){
        FilmsDto filmsSaved = filmsService.save(filmsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmsSaved);

    }

    @PostMapping("/{filmsId}/characters/{charactersId}")
    public ResponseEntity<Void> addCharacters(@PathVariable String filmsId, @PathVariable String charactersId){
        filmsService.addCharacters(filmsId, charactersId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}/addGenders/{gendersId}")
    public ResponseEntity<Void> addGenders(@PathVariable String id, @PathVariable String gendersId){
        filmsService.addGenders(id, gendersId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

 
    @PutMapping("/{filmsId}")
    public ResponseEntity<FilmsDto> editFilms(@PathVariable String filmsId, @RequestBody FilmsDto filmsDto){
        FilmsDto editedFilms = filmsService.editById(filmsId, filmsDto);
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
            @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        List<FilmsDto> filteredFilms = filmsService.getByFilters(title, genders, Integer.valueOf(order));
        return ResponseEntity.status(HttpStatus.OK).body(filteredFilms);
    }
}

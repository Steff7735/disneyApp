package com.disneyApp.controllers;

import com.disneyApp.DTO.CharactersDto;
import com.disneyApp.services.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("characters")
public class CharactersController {

    @Autowired
    private CharactersService charactersService;

    @GetMapping
    public ResponseEntity<List<CharactersDto>> getAll(){
        List<CharactersDto> characters = this.charactersService.getAllCharacters();
        return ResponseEntity.ok().body(characters);
    }


    @PostMapping
    public ResponseEntity<CharactersDto> save(@RequestBody CharactersDto characters){
        CharactersDto charactersSaved = charactersService.save(characters);
        return ResponseEntity.status(HttpStatus.CREATED).body(charactersSaved);

    }
}

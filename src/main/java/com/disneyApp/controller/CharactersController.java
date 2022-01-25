package com.disneyApp.controller;

import com.disneyApp.DTO.CharactersDto;
import com.disneyApp.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("characters")
public class CharactersController {

    @Autowired
    private CharactersService charactersService;

    @PostMapping
    public ResponseEntity<CharactersDto> save(@RequestBody CharactersDto characters){
        CharactersDto charactersSaved = charactersService.save(characters);
        return ResponseEntity.status(HttpStatus.CREATED).body(charactersSaved);

    }

    @GetMapping
    public ResponseEntity<List<CharactersDto>> getAll(){
        List<CharactersDto> characters = this.charactersService.getAll();
        return ResponseEntity.ok().body(characters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharactersDto> getById(@PathVariable String id) {
        CharactersDto characters = charactersService.getDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(characters);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CharactersDto> editCharacters(@PathVariable String id, @RequestBody CharactersDto characters) {
        CharactersDto editedCharacters = charactersService.editById(id, characters);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedCharacters);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        charactersService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<CharactersDto>> getByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) List<String> films
    ) {
        List<CharactersDto> filteredCharacters = charactersService.getByFilters(name, age, films);
        return ResponseEntity.status(HttpStatus.OK).body(filteredCharacters);
    }
}

package com.disneyApp.controller;


import com.disneyApp.DTO.GendersDto;
import com.disneyApp.service.GendersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
@RestController("genders")
public class GendersController {

    @Autowired
    private GendersService gendersService;

    @GetMapping
    public ResponseEntity<List<GendersDto>> getAll(){
        List<GendersDto> genders = this.gendersService.getAll();
        return ResponseEntity.ok().body(genders);
    }


    @PostMapping
    public ResponseEntity<GendersDto> save (@RequestBody GendersDto genders){
        GendersDto gendersSaved = gendersService.save(genders);
        return ResponseEntity.status(HttpStatus.CREATED).body(gendersSaved);

    }

    @PutMapping("/{id}")
    public ResponseEntity<GendersDto> editGenders(@PathVariable String id, @RequestBody GendersDto gendersToEdit){
        GendersDto editedGenders = gendersService.editById(id, gendersToEdit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedGenders);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        gendersService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

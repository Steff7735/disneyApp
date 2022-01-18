package com.disneyApp.controllers;


import com.disneyApp.DTO.GendersDto;
import com.disneyApp.services.GendersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("genders")
public class GendersController {

    @Autowired
    private GendersService gendersService;

    @GetMapping
    public ResponseEntity<List<GendersDto>> getAll(){
        List<GendersDto> genders = this.gendersService.getAllGenders();
        return ResponseEntity.ok().body(genders);
    }


    @PostMapping
    public ResponseEntity<GendersDto> save(@RequestBody GendersDto genders){
        GendersDto gendersSaved = gendersService.save(genders);
        return ResponseEntity.status(HttpStatus.CREATED).body(gendersSaved);

    }
}

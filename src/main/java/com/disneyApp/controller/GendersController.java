package com.disneyApp.controller;


import com.disneyApp.DTO.GendersDto;
import com.disneyApp.service.GendersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genders")
public class GendersController {

    @Autowired
    private GendersService gendersService;

    @GetMapping("/all")
    public ResponseEntity<List<GendersDto>> getAll(){
        List<GendersDto> genders = this.gendersService.getAllGenders();
        return ResponseEntity.ok().body(genders);
    }


    @PostMapping("/save")
    public ResponseEntity<GendersDto> save(@RequestBody GendersDto gendersDto){
        GendersDto gendersSaved = gendersService.savedNewGenders(gendersDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(gendersSaved);

    }

    @PutMapping("/{id}")
    public ResponseEntity<GendersDto> editGenders(@PathVariable String id, @RequestBody GendersDto gendersToEdit){
        GendersDto editedGenders = gendersService.editGendersById(id, gendersToEdit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedGenders);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        gendersService.deletedGendersById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

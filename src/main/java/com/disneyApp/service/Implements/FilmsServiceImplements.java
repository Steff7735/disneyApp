package com.disneyApp.service.Implements;

import com.disneyApp.DTO.FilmsDto;
import com.disneyApp.DTO.filters.FilmsFiltersDto;
import com.disneyApp.entity.Characters;
import com.disneyApp.entity.Films;

import com.disneyApp.entity.Genders;
import com.disneyApp.exception.NotFound;
import com.disneyApp.mapper.FilmsMapper;
import com.disneyApp.repository.FilmsRepository;
import com.disneyApp.repository.specification.FilmsSpecification;
import com.disneyApp.service.FilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmsServiceImplements implements FilmsService {

    @Autowired
    private FilmsMapper filmsMapper;
    @Autowired
    private FilmsRepository filmsRepository;
    @Autowired
    private CharactersServiceImplements charactersService;
    @Autowired
    private GendersServiceImplements gendersService;
    @Autowired
    private FilmsSpecification filmsSpecifications;

    @Override
    public FilmsDto save(FilmsDto filmsDto){
        Films entity = filmsMapper.filmsDto2Entity(filmsDto);
        Films SavedFilms = filmsRepository.save(entity);
        FilmsDto resultDto = filmsMapper.films2Dto(SavedFilms, false);

        return resultDto;

    }

    @Override
    public void addCharacters(String id, String charactersId) {
        Films savedFilms = this.handleById(id);
        Characters savedCharacters = charactersService.handleById(charactersId);
        savedFilms.getFilmsCharacters().size();
        savedFilms.addCharactersToFilms(savedCharacters);
        filmsRepository.save(savedFilms);
    }

    @Override
    public void addGenders(String id, String gendersId) {
        Films savedFilms = this.handleById(id);
        Genders savedGenders = gendersService.handleById(gendersId);
        savedFilms.getFilmsGenders().size();
        savedFilms.addGendersToFilms(savedGenders);
        filmsRepository.save(savedFilms);
    }


    @Override
    public List<FilmsDto> getAll() {
        List<Films> entities = filmsRepository.findAll();
        List<FilmsDto> resultDto = filmsMapper.filmsList2DtoList(entities, true);
        return resultDto;
    }

    @Override
    public FilmsDto getDetails(String id) {
        Films films = this.handleById(id);
        FilmsDto resultDto = filmsMapper.films2Dto(films, true);
        return resultDto;
    }

    @Override
    public void deleteById(String id) {
        filmsRepository.deleteById(id);

    }

    @Override
    public FilmsDto editById(String id, FilmsDto filmsDto) {
        Films savedFilms = this.handleById(id);
        savedFilms.setTitle(filmsDto.getTitle());
        savedFilms.setImage(filmsDto.getImage());
        savedFilms.setStars(filmsDto.getStars());
        savedFilms.setDateCreation(filmsDto.getDateCreation());
        Films editedFilms = filmsRepository.save(savedFilms);
        FilmsDto resultDto = filmsMapper.films2Dto(editedFilms, false);
        return resultDto;
    }

    @Override
    public List<FilmsDto> getByFilters(String title, List<String> genders, Integer order) {
        FilmsFiltersDto filtersDto = new FilmsFiltersDto();
        List<Films> entityList = filmsRepository.findAll(filmsSpecifications.getFiltered(filtersDto));
        List<FilmsDto> resultDto = filmsMapper.filmsList2DtoList(entityList, true);
        return resultDto;
    }


    @Override
    public Films handleById(String id) {
        Optional<Films> toBeFound = filmsRepository.findById(id);
        if(!toBeFound.isPresent()) {
            throw new NotFound("No Film for id: " + id);
        }
        return toBeFound.get();
    }
}

package com.disneyApp.service.Implements;

import com.disneyApp.Dto.FilmsDto;
import com.disneyApp.Dto.filters.FilmsFiltersDto;

import com.disneyApp.entity.Characters;
import com.disneyApp.entity.Films;
import com.disneyApp.entity.Genders;
import com.disneyApp.exception.ParamNotFound;
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


    public FilmsDto save(FilmsDto filmsDto){
        Films entity = filmsMapper.filmsDto2Entity(filmsDto);
        Films SavedFilms = filmsRepository.save(entity);
        FilmsDto resultDto = filmsMapper.films2Dto(SavedFilms, false);

        return resultDto;

    }


    public void addCharacters(String filmsId, String charactersId) {
        Films savedFilms = this.handleById(filmsId);
        Characters savedCharacters = charactersService.handleById(charactersId);
        savedFilms.getCharacters().size();
        savedFilms.addCharacters(savedCharacters);
        filmsRepository.save(savedFilms);
    }


    public void addGenders(String filmsId, String gendersId) {
        Films savedFilms = this.handleById(filmsId);
        Genders savedGenders = gendersService.handleById(gendersId);
        savedFilms.getGenders().size();
        savedFilms.addGenders(savedGenders);
        filmsRepository.save(savedFilms);
    }

    public List<FilmsDto> getAll() {
        List<Films> entities = filmsRepository.findAll();
        List<FilmsDto> resultDto = filmsMapper.filmsList2DtoList(entities, true);
        return resultDto;
    }

    public FilmsDto getDetails(String filmsId) {
        Films films = this.handleById(filmsId);
        FilmsDto resultDto = filmsMapper.films2Dto(films, true);
        return resultDto;
    }


    public void deleteById(String filmsId) {
        filmsRepository.deleteById(filmsId);

    }


    public FilmsDto editById(String filmsId, FilmsDto filmsDto) {
        Films savedFilms = this.handleById(filmsId);
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
    public Films handleById(String filmsId) {
        Optional<Films> toBeFound = filmsRepository.findById(filmsId);
        if(!toBeFound.isPresent()) {
            throw new ParamNotFound("No Film for id: " + filmsId);
        }
        return toBeFound.get();
    }
}

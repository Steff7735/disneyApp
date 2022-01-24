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
import org.springframework.data.domain.Sort;
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
    public FilmsDto savedNewFilms(FilmsDto newFilms){
        Films entity = filmsMapper.filmsDto2Entity(newFilms);
        Films SavedFilms = filmsRepository.save(entity);
        FilmsDto resultDto = filmsMapper.films2Dto(SavedFilms, false);

        return resultDto;

    }

    @Override
    public void addCharactersToFilms(String id, String charactersId) {
        Films savedFilms = this.handleFindById(id);
        Characters savedCharacters = charactersService.handleFindById(charactersId);
        savedFilms.getCharacters().size();
        savedFilms.addCharactersToFilms(savedCharacters);
        filmsRepository.save(savedFilms);
    }

    @Override
    public void addGendersToFilms(String id, String gendersId) {
        Films savedFilms = this.handleFindById(id);
        Genders savedGenders = gendersService.handleFindById(gendersId);
        savedFilms.getGenders().size();
        savedFilms.addGendersToFilms(savedGenders);
        filmsRepository.save(savedFilms);
    }


    public List<FilmsDto> getAllFilms() {
        List<Films> entities = filmsRepository.findAll();
        List<FilmsDto> resultDto = filmsMapper.filmsList2DtoList(entities, true);
        return resultDto;
    }

    @Override
    public FilmsDto getFilmsDetails(String id) {
        Films films = this.handleFindById(id);
        FilmsDto resultDto = filmsMapper.films2Dto(films, true);
        return resultDto;
    }

    @Override
    public void deleteFilmsById(String id) {
        filmsRepository.deleteById(id);

    }

    @Override
    public FilmsDto editFilmsById(String id, FilmsDto filmsToEdit) {
        Films savedFilms = this.handleFindById(id);
        savedFilms.setTitle(filmsToEdit.getTitle());
        savedFilms.setImage(filmsToEdit.getImage());
        savedFilms.setStars(filmsToEdit.getStars());
        savedFilms.setDateCreation(filmsToEdit.getDateCreation());
        Films editedFilms = filmsRepository.save(savedFilms);
        FilmsDto resultDto = filmsMapper.films2Dto(editedFilms, false);
        return resultDto;
    }

    @Override
    public List<FilmsDto> getByFilters(String name, List<String> genders, Integer order) {
        FilmsFiltersDto filmsFilters = new FilmsFiltersDto();
        List<Films> entityList = filmsRepository.findAll((Sort) filmsSpecifications.getFiltered(filmsFilters));
        List<FilmsDto> resultDto = filmsMapper.filmsList2DtoList(entityList, true);
        return resultDto;
    }



    // --- ERROR HANDLING ---
    public Films handleFindById(String id) {
        Optional<Films> toBeFound = filmsRepository.findById(id);
        if(!toBeFound.isPresent()) {
            throw new NotFound("No Film for id: " + id);
        }
        return toBeFound.get();
    }
}

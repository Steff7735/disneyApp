package com.disneyApp.service.Implements;

import com.disneyApp.DTO.CharactersDto;
import com.disneyApp.DTO.filters.CharactersFiltersDto;
import com.disneyApp.entity.Characters;
import com.disneyApp.exception.NotFound;
import com.disneyApp.mapper.CharactersMapper;
import com.disneyApp.repository.CharactersRepository;
import com.disneyApp.repository.specification.CharactersSpecification;
import com.disneyApp.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CharactersServiceImplements implements CharactersService {

    @Autowired
    private CharactersMapper charactersMapper;
    @Autowired
    private CharactersRepository charactersRepository;

    @Autowired
    private CharactersSpecification charactersSpecifications;

    @Override
    public CharactersDto save(CharactersDto newCharacters){
        Characters entity = charactersMapper.charactersDto2Entity(newCharacters);
        Characters entitySaved = charactersRepository.save(entity);
        CharactersDto result = charactersMapper.characters2Dto(entitySaved, true);

        return result;

    }
    
    @Override
    public List<CharactersDto> getAllCharacters() {
        List<Characters> list = charactersRepository.findAll();
        List<CharactersDto> dtoList = charactersMapper.charactersList2DtoList(list, true);
        return dtoList;
    }


   @Override
   public CharactersDto getCharactersDetails(String id) {
       Characters detailsCharacters = this.handleFindById(id);
       CharactersDto resultDto = charactersMapper.characters2Dto(detailsCharacters, true);
       return resultDto;
   }
   
    @Override
    public CharactersDto editCharactersById(String id, CharactersDto charactersToEdit) {
        Characters savedCharacters = this.handleFindById(id);
        savedCharacters.setName(charactersToEdit.getName());
        savedCharacters.setAge(charactersToEdit.getAge());
        savedCharacters.setWeight(charactersToEdit.getWeight());
        savedCharacters.setHistory(charactersToEdit.getHistory());
        savedCharacters.setFilmsAsoc(charactersToEdit.getFilmsAsoc());
        Characters editedCharacters = charactersRepository.save(savedCharacters);
        CharactersDto resultDto = charactersMapper.characters2Dto(editedCharacters, false);
        return resultDto;
    }

    @Override
    public void deleteCharactersById(String id) {

        charactersRepository.deleteById(id);
    }

    @Override
    public List<CharactersDto> getByFilters(String name, Integer age, List<String> films) {
        CharactersFiltersDto filtersDto = new CharactersFiltersDto(name, age, films);
        List<Characters> charactersList = charactersRepository.findAll(charactersSpecifications.getFiltered(filtersDto));
        List<CharactersDto> charactersDtos = charactersMapper.charactersList2DtoList(charactersList,false);
        return charactersDtos;
    }

   
    public Characters handleFindById(String id) {
        Optional<Characters> toBeFound = charactersRepository.findById(id);
        if(!toBeFound.isPresent()) {
            throw new NotFound("No Character for id: " + id);
        }
        return toBeFound.get();
    }
}

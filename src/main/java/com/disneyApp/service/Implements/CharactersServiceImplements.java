package com.disneyApp.service.Implements;

import com.disneyApp.Dto.CharactersDto;
import com.disneyApp.Dto.filters.CharactersFiltersDto;
import com.disneyApp.entity.Characters;
import com.disneyApp.exception.ParamNotFound;
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
    public CharactersDto save(CharactersDto charactersDto){
        Characters characters = charactersMapper.charactersDto2Entity(charactersDto);
        Characters charactersSaved = charactersRepository.save(characters);
        CharactersDto result = charactersMapper.characters2Dto(charactersSaved, true);

        return result;

    }
    
    @Override
    public List<CharactersDto> getAll() {
        List<Characters> list = charactersRepository.findAll();
        List<CharactersDto> dtoList = charactersMapper.charactersList2DtoList(list, true);
        return dtoList;
    }


   @Override
   public CharactersDto getDetails(String charactersId) {
       Characters characters = this.handleById(charactersId);
       CharactersDto resultDto = charactersMapper.characters2Dto(characters, true);
       return resultDto;
   }
   
    @Override
    public CharactersDto editById(String charactersId, CharactersDto charactersDto) {
        Characters savedCharacters = this.handleById(charactersId);
        savedCharacters.setName(charactersDto.getName());
        savedCharacters.setAge(charactersDto.getAge());
        savedCharacters.setWeight(charactersDto.getWeight());
        savedCharacters.setHistory(charactersDto.getHistory());
        savedCharacters.setFilmsA(charactersDto.getFilmsA());
        Characters editedCharacters = charactersRepository.save(savedCharacters);
        CharactersDto resultDto = charactersMapper.characters2Dto(editedCharacters, false);
        return resultDto;
    }

    @Override
    public void deleteById(String charactersId) {

        charactersRepository.deleteById(charactersId);
    }

    @Override
    public List<CharactersDto> getByFilters(String name, Integer age, List<String> films) {
        CharactersFiltersDto filtersDto = new CharactersFiltersDto(name, age, films);
        List<Characters> characters = charactersRepository.findAll(charactersSpecifications.getFiltered(filtersDto));
        List<CharactersDto> charactersDto = charactersMapper.charactersList2DtoList(characters,false);
        return charactersDto;
    }

   
    public Characters handleById(String charactersId) {
        Optional<Characters> toBeFound = charactersRepository.findById(charactersId);
        if(!toBeFound.isPresent()) {
            throw new ParamNotFound("No Character for charactersId: " + charactersId);
        }
        return toBeFound.get();
    }
}

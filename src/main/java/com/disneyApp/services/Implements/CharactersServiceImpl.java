package com.disneyApp.services.Implements;

import com.disneyApp.DTO.CharactersDto;
import com.disneyApp.entity.CharactersEntity;
import com.disneyApp.mappers.CharactersMapper;
import com.disneyApp.repository.CharactersRepository;
import com.disneyApp.services.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CharactersServiceImpl implements CharactersService {

    @Autowired
    private CharactersMapper charactersMapper;
    @Autowired
    private CharactersRepository charactersRepository;

//    @Autowired
//    CharacterSpecification characterSpec;

    @Override
    public CharactersDto save(CharactersDto dto){
        CharactersEntity entity = charactersMapper.charactersDto2Entity(dto);
        CharactersEntity entitySaved = charactersRepository.save(entity);
        CharactersDto result = charactersMapper.charactersEntity2Dto(entitySaved);

        return result;

    }
    
    @Override
    public List<CharactersDto> getAllCharacters() {
        List<CharactersEntity> entities = charactersRepository.findAll();
        List<CharactersDto> result = charactersMapper.charactersEntityList2DtoList(entities);
        return result;
    }

//    @Override
//    public CharactersDto getCharactersDetails(String id) {
//        CharactersEntity dbCharacters = this.handleFindById(id);
//        CharactersDto resultDto = charactersMapper.charactersEntity2Dto(dbCharacters,true);
//        return resultDto;
//    }
//
//    @Override
//    public CharactersDto editCharactersById(String id, CharactersDto charactersToEdit) {
//        CharactersEntity savedCharacters = this.handleFindById(id);
//        savedCharacters.setName(charactersToEdit.getName());
//        savedCharacters.setAge(charactersToEdit.getAge());
//        savedCharacters.setWeight(charactersToEdit.getWeight());
//        savedCharacters.setHistory(charactersToEdit.getHistory());
//        savedCharacters.setFilmsAsoc(charactersToEdit.getFilmsAsoc());
//        CharactersEntity editedCharacters = charactersRepository.save(savedCharacters);
//        CharactersDto resultDto = charactersMapper.charactersEntity2Dto(editedCharacters, false);
//        return resultDto;
//    }
//
//    public void deleteCharactersById(String id) {
//        charactersRepository.deleteById(id);
//    }
//
//    @Override
//    public List<CharactersDto> getByFilters(String name, Integer age, List<String> movies) {
//        CharactersFiltersDto filtersDto = new CharactersFiltersDto(name, age, movies);
//        List<CharactersEntity> entityList = charactersRepository.findAll(charactersSpec.getFiltered(filtersDto));
//        List<CharactersDto> resultDto = charactersMapper.charactersEntityList2DTOList(entityList, true);
//        return resultDto;
//    }
//
//    // --- ERROR HANDLING ---
//    public CharacterEntity handleFindById(Long id) {
//        Optional<CharacterEntity> toBeFound = characterRepository.findById(id);
//        if(!toBeFound.isPresent()) {
//            throw new ParamNotFound("No Character for id: " + id);
//        }
//        return toBeFound.get();
//    }
}

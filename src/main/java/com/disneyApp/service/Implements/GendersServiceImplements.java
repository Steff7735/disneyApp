package com.disneyApp.service.Implements;

import com.disneyApp.DTO.GendersDto;
import com.disneyApp.entity.Genders;
import com.disneyApp.exception.NotFound;
import com.disneyApp.mapper.GendersMapper;
import com.disneyApp.repository.GendersRepository;
import com.disneyApp.service.GendersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GendersServiceImplements implements GendersService {

    @Autowired
    private GendersMapper gendersMapper;
    @Autowired
    private GendersRepository gendersRepository;

    public GendersDto savedNewGenders(GendersDto newGenders){
        Genders entity = gendersMapper.gendersDto2Entity(newGenders);
        Genders savedGenders = gendersRepository.save(entity);
        GendersDto resultDto = gendersMapper.genders2Dto(savedGenders,false);

        return resultDto;

    }

    public List<GendersDto> getAllGenders() {
        List<Genders> entities = gendersRepository.findAll();
        List<GendersDto> resultDto = this.gendersMapper.gendersList2DtoList(entities,false);
        return resultDto;
    }

    @Override
    public void deletedGendersById(String id) {

        gendersRepository.deleteById(id);
    }

    @Override
    public GendersDto editGendersById(String id, GendersDto gendersToEdit) {
        Genders savedGenders = this.handleFindById(id);
        savedGenders.setNombre(savedGenders.getNombre());
        savedGenders.setImagen(savedGenders.getImagen());
        savedGenders.setFilmsAsoc(savedGenders.getFilmsAsoc());
        Genders editedGenders = gendersRepository.save(savedGenders);
        GendersDto resultDto = gendersMapper.genders2Dto(editedGenders,false);

        return resultDto;

    }

    public Genders handleFindById(String id) {
        Optional<Genders> found = gendersRepository.findById(id);
        if (!found.isPresent()) {
            throw new NotFound("No Genders for id: " + id);
        }
        return found.get();
    }
 }

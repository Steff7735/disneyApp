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

    @Override
    public GendersDto save(GendersDto gendersDto){
        Genders genders = gendersMapper.gendersDto2Entity(gendersDto);
        Genders savedGenders = gendersRepository.save(genders);
        GendersDto resultDto = gendersMapper.genders2Dto(savedGenders,false);

        return resultDto;

    }

    @Override
    public List<GendersDto> getAll() {
        List<Genders> genders = gendersRepository.findAll();
        List<GendersDto> resultDto = this.gendersMapper.gendersList2DtoList(genders,false);
        return resultDto;
    }

    @Override
    public void deleteById(String id) {

        gendersRepository.deleteById(id);
    }

    @Override
    public GendersDto editById(String id, GendersDto gendersDto) {
        Genders savedGenders = this.handleById(id);
        savedGenders.setNombre(savedGenders.getNombre());
        savedGenders.setImagen(savedGenders.getImagen());
        savedGenders.setFilmsAsoc(savedGenders.getFilmsAsoc());
        Genders editedGenders  = gendersRepository.save(savedGenders);
        GendersDto resultDto = gendersMapper.genders2Dto(editedGenders,false);

        return resultDto;

    }

    @Override
    public Genders handleById(String id) {
        Optional<Genders> found = gendersRepository.findById(id);
        if (!found.isPresent()) {
            throw new NotFound("No Genders for id: " + id);
        }
        return found.get();
    }
 }

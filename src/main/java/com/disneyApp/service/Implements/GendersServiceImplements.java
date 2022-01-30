package com.disneyApp.service.Implements;

import com.disneyApp.Dto.GendersDto;
import com.disneyApp.entity.Genders;
import com.disneyApp.exception.ParamNotFound;
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
        GendersDto result = gendersMapper.genders2Dto(savedGenders,false);

        return result;

    }

    @Override
    public List<GendersDto> getAll() {
        List<Genders> genders = gendersRepository.findAll();
        List<GendersDto> listDto = this.gendersMapper.gendersList2DtoList(genders,false);
        return listDto;
    }

    @Override
    public void deleteById(String gendersId) {

        gendersRepository.deleteById(gendersId);
    }

    @Override
    public GendersDto editById(String gendersId, GendersDto gendersDto) {
        Genders genders = this.handleById(gendersId);
        genders.setName(gendersDto.getName());
        genders.setImage(gendersDto.getImage());
        genders.setFilmsA(gendersDto.getFilmsA());
        Genders editedGenders  = gendersRepository.save(genders);
        GendersDto editDto = gendersMapper.genders2Dto(editedGenders,false);

        return editDto;

    }

    @Override
    public Genders handleById(String gendersId) {
        Optional<Genders> found = gendersRepository.findById(gendersId);
        if (!found.isPresent()) {
            throw new ParamNotFound("No Genders for id: " + gendersId);
        }
        return found.get();
    }
 }

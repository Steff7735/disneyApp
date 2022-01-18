package com.disneyApp.services.Implements;

import com.disneyApp.DTO.GendersDto;
import com.disneyApp.entity.GendersEntity;
import com.disneyApp.mappers.GendersMapper;
import com.disneyApp.repository.GendersRepository;
import com.disneyApp.services.GendersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GendersServiceImpl implements GendersService {

    @Autowired
    private GendersMapper gendersMapper;
    @Autowired
    private GendersRepository gendersRepository;

    @Override
    public GendersDto save(GendersDto dto){
        GendersEntity entity = gendersMapper.gendersDto2Entity(dto);
        GendersEntity entitySaved = gendersRepository.save(entity);
        GendersDto result = gendersMapper.gendersEntity2Dto(entitySaved);

        return result;

    }

    @Override
    public List<GendersDto> getAllGenders() {
        List<GendersEntity> entities = gendersRepository.findAll();
        List<GendersDto> result = gendersMapper.gendersEntityList2DtoList(entities);
        return result;
    }
}

package com.disneyApp.services.Implements;

import com.disneyApp.DTO.FilmsDto;
import com.disneyApp.entity.FilmsEntity;
import com.disneyApp.mappers.FilmsMapper;
import com.disneyApp.repository.FilmsRepository;
import com.disneyApp.services.FilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmsServiceImpl implements FilmsService {

    @Autowired
    private FilmsMapper filmsMapper;
    @Autowired
    private FilmsRepository filmsRepository;

    @Override
    public FilmsDto save(FilmsDto dto){
        FilmsEntity entity = filmsMapper.filmsDto2Entity(dto);
        FilmsEntity entitySaved = filmsRepository.save(entity);
        FilmsDto result = filmsMapper.filmsEntity2Dto(entitySaved);

        return result;

    }

    @Override
    public List<FilmsDto> getAllFilms() {
        List<FilmsEntity> entities = filmsRepository.findAll();
        List<FilmsDto> result = filmsMapper.filmsEntityList2DtoList(entities);
        return result;
    }
}

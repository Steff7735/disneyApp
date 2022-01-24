package com.disneyApp.repository;

import com.disneyApp.entity.Films;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmsRepository extends JpaRepository <Films, String>, JpaSpecificationExecutor<Films> {
    @Override
    List<Films> findAll(Specification<Films> specification);
}

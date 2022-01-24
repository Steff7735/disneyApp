package com.disneyApp.repository;

import com.disneyApp.entity.Characters;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CharactersRepository extends JpaRepository <Characters, String>, JpaSpecificationExecutor<Characters> {
    
   
    @Override
    List<Characters> findAll(Specification<Characters> specification);
    
}

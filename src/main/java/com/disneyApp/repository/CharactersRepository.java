package com.disneyApp.repository;

import com.disneyApp.entity.CharactersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharactersRepository extends JpaRepository <CharactersEntity, String> {
}

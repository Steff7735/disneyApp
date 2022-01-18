package com.disneyApp.repository;

import com.disneyApp.entity.FilmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmsRepository extends JpaRepository <FilmsEntity , String> {
}

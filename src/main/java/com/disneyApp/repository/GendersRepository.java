package com.disneyApp.repository;

import com.disneyApp.entity.GendersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GendersRepository extends JpaRepository <GendersEntity, String> {
}

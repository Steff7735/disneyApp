package com.disneyApp.repository;

import com.disneyApp.entity.Genders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GendersRepository extends JpaRepository <Genders, String> {

}

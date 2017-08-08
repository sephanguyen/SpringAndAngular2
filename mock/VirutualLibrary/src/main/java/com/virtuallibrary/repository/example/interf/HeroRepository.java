package com.virtuallibrary.repository.example.interf;

import com.virtuallibrary.model.example.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Long>, HeroRepositoryCustom {
    List<Hero> findByName(String name);
    List<Hero> findByNameContaining(String name);
}

package com.virtuallibrary.repository.example;

import com.virtuallibrary.model.example.Hero;
import com.virtuallibrary.repository.BaseRepository;
import com.virtuallibrary.repository.example.interf.HeroRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public class HeroRepositoryImpl extends BaseRepository<Hero, Long> implements HeroRepositoryCustom {
}

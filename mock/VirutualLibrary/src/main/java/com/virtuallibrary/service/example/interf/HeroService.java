package com.virtuallibrary.service.example.interf;

import com.virtuallibrary.model.example.Hero;
import com.virtuallibrary.service.IBaseService;

import java.util.List;

public interface HeroService extends IBaseService<Hero, Long> {
	List<Hero> findByName(String name);
	List<Hero> findByNameContaining(String name);
}

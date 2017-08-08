package com.virtuallibrary.service.example;

import com.virtuallibrary.model.example.Hero;
import com.virtuallibrary.repository.example.interf.HeroRepository;
import com.virtuallibrary.service.BaseService;
import com.virtuallibrary.service.example.interf.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroServiceImpl extends BaseService<Hero, Long> implements HeroService {

	@Autowired
	HeroRepository heroRepository;

	@Override
	public Hero findOne(Long id) {
		return heroRepository.findOne(id);
	}

//	@Override
//	public Hero findOne(Long id) {
//		Hero hero = null;
//		try {
//			hero = heroRepository.findOne(id);
//		} catch (TransactionSystemException e) {
//			return null;
//		}
//		return hero;
//	}

	@Override
	public List<Hero> findAll() {
		return (List<Hero>) heroRepository.findAll();
	};

	@Override
	public List<Hero> findAll(List<Long> listId) {
		return (List<Hero>) heroRepository.findAll(listId);
	}

	@Override
	public long count() {
		return heroRepository.count();
	}

	@Override
	public boolean exists(Long id) {
		return heroRepository.exists(id);
	}

	@Override
	public Hero save(Hero hero) {
		return heroRepository.save(hero);
	}

	@Override
	public List<Hero> save(List<Hero> listHero) {
		return ( List<Hero>) heroRepository.save(listHero);
	}

	@Override
	public void delete(Long id) {
		this.heroRepository.delete(id);
	}

	@Override
	public void delete(Hero entity) {
		this.heroRepository.delete(entity);
	}

	@Override
	public void delete(List<Hero> listEntity) {
		heroRepository.delete(listEntity);
	}

	@Override
	public void deleteAll() {
		heroRepository.deleteAll();
	}

	@Override
	public List<Hero> findByName(String name) {
		return heroRepository.findByName(name);
	}

	@Override
	public List<Hero> findByNameContaining(String name) {
		return heroRepository.findByNameContaining(name);
	}
}

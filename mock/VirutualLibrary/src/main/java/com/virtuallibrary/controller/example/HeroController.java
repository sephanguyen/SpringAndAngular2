package com.virtuallibrary.controller.example;

import com.virtuallibrary.controller.BaseController;
import com.virtuallibrary.model.example.Hero;
import com.virtuallibrary.service.example.interf.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/heroes")
public class HeroController extends BaseController {
	
	@Autowired
	private HeroService heroService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Hero> getAllHeroes() {
		return heroService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Hero getHeroById(@PathVariable Long id) {
		return heroService.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Hero updateHero(@RequestBody Hero hero) {
		return heroService.save(hero);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Hero createHero(@RequestBody Hero hero) {
		return heroService.save(hero);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteHeroById(@PathVariable Long id) {
		heroService.delete(id);
	}

//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	public String handleException(Exception e) {
//		return e.getMessage();
//	}
}

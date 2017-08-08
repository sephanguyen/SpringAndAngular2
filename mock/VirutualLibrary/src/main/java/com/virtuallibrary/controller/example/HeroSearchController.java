package com.virtuallibrary.controller.example;

import com.virtuallibrary.model.example.Hero;
import com.virtuallibrary.service.example.interf.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ThuanXT1 on 2017/02/20.
 */
@RestController
@RequestMapping("/app/heroes")
public class HeroSearchController {
    @Autowired
    private HeroService heroService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Hero> findHeroByName(@RequestParam String name) {
        return heroService.findByNameContaining(name);
    }
}

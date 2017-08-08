package com.virtuallibrary;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.virtuallibrary.model.book.Book;
import com.virtuallibrary.model.example.Hero;
import com.virtuallibrary.service.book.interf.BookService;
import com.virtuallibrary.service.example.interf.HeroService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

    @Autowired
    private MockMvc mvc;
    
    private String expected = "[{\"id\":1,\"name\":\"Hero 1\"},{\"id\":2,\"name\":\"Hero 2\"},{\"id\":3,\"name\":\"Hero 3\"},{\"id\":4,\"name\":\"Hero 4\"},{\"id\":5,\"name\":\"Hero 5\"},{\"id\":6,\"name\":\"Hero 6\"},{\"id\":7,\"name\":\"Hero 7\"},{\"id\":8,\"name\":\"Hero 8\"},{\"id\":9,\"name\":\"Hero 9\"},{\"id\":10,\"name\":\"Hero 10\"}]";

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/heroes").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expected)));
    }
    
    @Test public void sample() {
        assertEquals("Gradle is gr8", "Gradle is gr8");
    }
    
    @Autowired
	private HeroService heroService;
	  
	  @Before
	  public void setUp() {
		  Hero hero1 = new Hero(1L, "Superman");
		  heroService.save(hero1);
		  Hero hero2 = new Hero(2L, "Batman");
		  heroService.save(hero2);
	  }
	  
	  @Test
	  public void TestFindAll() {
		  
		  List<Hero> heroes = heroService.findAll();
		  Assert.assertNotNull("failure - expected not null", heroes);
	      Assert.assertEquals("failure - expected list size", 10, heroes.size());
	  }
	
	  @Test
	  public void testFindOneHeroes() {
		  
		  Long id = new Long(1);
		  
		  Hero hero = heroService.findOne(id);
		  
		  Assert.assertNotNull("failure - expected not null", hero);
		  Assert.assertEquals("failure - expected id not match", id, hero.getId());
		  
	  }
	  
	  @Test
	  public void testFindOneHeroNotFound() {
		  //Long id = Long.MAX_VALUE;
		  Long id = new Long(12);
		  
		  Hero hero = heroService.findOne(id);
		  
		  Assert.assertNull("failure - expected null", hero);
	  }
	  
	  @Test 
	  public void testCreateHero() {
		  Hero newHero = new Hero(11L, "Wonder Woman");
		  
		  newHero = heroService.save(newHero);
		  
		  Assert.assertNotNull("failure - expected not null" ,newHero);
		  Assert.assertNotNull("failure - expected id Atrtibute not null" ,newHero.getId());
		  
		  Assert.assertEquals("failure - expected book name not match", newHero.getName(), newHero.getName()); 
		  
		  List<Hero> listHero = heroService.findAll();
		  Assert.assertEquals("failure - expected size", 11, listHero.size());
	  }
	  
	  
	  @Test
	  public void testUpdateHero() {
		  
		  Long id = new Long(1);
		  
		  Hero hero = heroService.findOne(id);
		  
		  Assert.assertNotNull("failure - expected not null", hero);
		  
		  String updateName = "Green Lantern";
		  hero.setName(updateName);
		  hero = heroService.save(hero);
		  Assert.assertNotNull("failure - expected not null", hero);
	        Assert.assertEquals("failure - expected id attribute match", id,
	        		hero.getId());
	        Assert.assertEquals("failure - expected name attribute match",
	        		updateName, hero.getName());
	  }
	  
//	  @Test
//	  public void testUpdateNotFound() {
//		  Exception exception = null;
//		  Long id = new Long(99);
//		  Hero hero = new Hero(id, "Joker");
//		  try {
//	            heroService.save(hero);
//	        } catch (NoResultException e) {
//	            exception = e;
//	        }
//		  Assert.assertNotNull("failure - expected exception", exception);
//	      Assert.assertTrue("failure - expected NoResultException",
//	                exception instanceof NoResultException);
//	  }
	  
	  @Test
	  public void testDeleteHero() {
		  Long id = new Long(1);
		  Hero hero = heroService.findOne(id);
		  
		  Assert.assertNotNull("failure - expected not null", hero);
		  
		  heroService.delete(hero.getId());
		  
		  //List<Hero> listBook = heroService.delete(hero);
		  
		  //Assert.assertEquals("failure - expected size", 1, listBook.size());
		  
		  hero = heroService.findOne(id);
		  Assert.assertNull("failure - expected null", hero);
	  }
    
    
}
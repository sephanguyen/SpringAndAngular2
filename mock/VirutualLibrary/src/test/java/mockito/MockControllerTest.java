package mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.virtuallibrary.controller.example.HeroController;
import com.virtuallibrary.model.example.Hero;
import com.virtuallibrary.service.example.interf.HeroService;


@RunWith(MockitoJUnitRunner.class)
public class MockControllerTest {

	// @InjectMocks
	private HeroController heroController;

	// @Mock
	private HeroService heroService;
	
	//List<Hero> AllHeroList = Arrays.asList(new Hero(1L, "Hero 1"), new Hero(2L, "Hero 2"));

	@Before
	public void setUpMock() {
		heroController = new HeroController();
		heroService = mock(HeroService.class);
		//heroController.setService(heroService);
		
	}

	@Test
	public void getAllHeroes() {
		//List<Hero> expectedAllHeroList = Arrays.asList(new Hero(1L, "Hero 1"), new Hero(2L, "Hero 2"));
		when(heroService.findAll()).thenReturn(Arrays.asList(new Hero(1L, "Hero 1"), new Hero(2L, "Hero 2")));

		int expectedHeroNum = 2;
		int ActualHeroNum = heroController.getAllHeroes().size();

		Assert.assertEquals(expectedHeroNum, ActualHeroNum);
		verify(heroService).findAll();

	}

	@Test
	public void getHeroById() {
		// expecteHero = new Hero(2L, "Hero 2");
		List<Hero> AllHeroList = Arrays.asList(new Hero(1L, "Hero 1"), new Hero(2L, "Hero 2"));
		when(heroService.findOne(2L)).thenReturn(AllHeroList.get(1));

		Hero actualHero = heroController.getHeroById(2L);
		
		long expectedHeroID = 2L;
		long actualHeroID = actualHero.getId();

		String expectedHeroName = "Hero 2";
		String actualHeroName = actualHero.getName();

		Assert.assertEquals(expectedHeroID, actualHeroID);
		Assert.assertEquals(expectedHeroName, actualHeroName);

	}
	
	@Test
	public void updateHero() {
		Hero hero = new Hero(2L, "Hero 2");
		Hero expecteHero = new Hero(2L, "Hero 3");
		
		List<Hero> AllHeroExpectedList = Arrays.asList(new Hero(1L, "Hero 1"), new Hero(2L, "Hero 3"));
		
		when(heroService.save(hero)).thenReturn(AllHeroExpectedList.get(1));
		
		hero = heroController.updateHero(hero);

		Assert.assertEquals(hero.getName(), expecteHero.getName());

	}
	
//	@Test
//	public void createHero() {
//		Hero hero = new Hero(3L, "Hero 3");
//		
//		List<Hero> AllHeroExpectedList = Arrays.asList(new Hero(1L, "Hero 1"), new Hero(2L, "Hero 2"));
//		
//		when(heroService.save(hero)).thenReturn(AllHeroExpectedList.get(1));
//		
//		hero = heroController.createHero(hero);
//
//		Assert.assertEquals(hero.getName(), AllHeroExpectedList.get(1).getName());
//		Assert.assertEquals(AllHeroExpectedList.size(), 3);
//
//	}
//	
//	@Test
//	public void deleteHeroById() {
//		//Hero hero = new Hero(3L, "Hero 3");
//		
//		List<Hero> AllHeroExpectedList = Arrays.asList(new Hero(1L, "Hero 1"), new Hero(2L, "Hero 2"));
//		
//
//
//		//Assert.assertEquals(hero.getName(), AllHeroExpectedList.get(2).getName());
//
//	}

}

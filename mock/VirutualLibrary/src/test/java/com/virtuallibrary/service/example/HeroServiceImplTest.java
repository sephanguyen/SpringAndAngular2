package com.virtuallibrary.service.example;

import com.virtuallibrary.model.example.Hero;
import com.virtuallibrary.repository.example.interf.HeroRepository;
import com.virtuallibrary.service.example.interf.HeroService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by thuan_000 on 3/25/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HeroServiceImplTest {
    @MockBean
    HeroRepository mockHeroRepository;

    @Autowired
    HeroService heroService;

    @Test
    public void findOneTest1() throws SQLException {
        // prepare mock repository
        given(this.mockHeroRepository.findOne(1L)).willReturn(new Hero(1L, "Hero Test"));

        // call Hero Service find One
        Hero hero = this.heroService.findOne(1L);

        // assert Result
        assertThat(hero.getId()).isEqualTo(1L);
        assertThat(hero.getName()).isEqualTo("Hero Test");
    }

    @Test
    public void findOneTest2() throws SQLException {
        // prepare mock repository
        given(this.mockHeroRepository.findOne(1L)).willReturn(new Hero());

        // call Hero Service find One
        Hero hero = this.heroService.findOne(1L);

        // assert Result
        assertThat(hero.getId()).isNull();
        assertThat(hero.getName()).isNull();
    }

    @Test
    public void findOneTest3() throws SQLException {
        // prepare mock repository
        given(this.mockHeroRepository.findOne(1L)).willReturn(null);

        // call Hero Service find One
        Hero hero = this.heroService.findOne(1L);

        // assert Result
        assertThat(hero).isNull();
    }

    @Test
    public void findOneTest4() throws SQLException {
        // prepare mock repository
        given(this.mockHeroRepository.findOne(1L)).willThrow(
                new TransactionSystemException("Transaction Error"));

        // call Hero Service find One
        Hero hero = this.heroService.findOne(1L);

        // assert Result
        assertThat(hero).isNull();
    }
}

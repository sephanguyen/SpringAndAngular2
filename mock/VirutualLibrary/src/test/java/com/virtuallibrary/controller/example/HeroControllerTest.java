package com.virtuallibrary.controller.example;

import com.virtuallibrary.model.example.Hero;
import com.virtuallibrary.service.example.interf.HeroService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.exceptions.misusing.NullInsteadOfMockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by thuan_000 on 3/25/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HeroControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HeroService mockHeroService;

    @Test
    public void getHeroByIdTest1() throws Exception {
        // Prepared mock service
        given(this.mockHeroService.findOne(1L)).willReturn(new Hero(1L, "Hero Test"));

        // perform getHeroById
        this.mvc.perform(get("/api/heroes/1").accept(MediaType.APPLICATION_JSON))
                // check result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Hero Test")));
        ;
    }

    @Test
    public void getHeroByIdTest2() throws Exception {
        // Prepared mock service
        given(this.mockHeroService.findOne(11L)).willReturn(null);

        // perform getHeroById
        this.mvc.perform(get("/api/heroes/11").accept(MediaType.APPLICATION_JSON))
                // check result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.name").doesNotExist());
    }

    @Test
    public void getHeroByIdTest3() throws Exception {
        // Prepared mock service
        given(this.mockHeroService.findOne(5L)).willReturn(new Hero());

        // perform getHeroById
        this.mvc.perform(get("/api/heroes/5").accept(MediaType.APPLICATION_JSON))
                // check result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(nullValue())))
                .andExpect(jsonPath("$.name", is(nullValue())));
    }

    @Test
    public void getHeroByIdTest4() throws Exception {
        // Prepared mock service
        given(this.mockHeroService.findOne(20L)).willThrow(new NullInsteadOfMockException("Hero not exists"));

        // perform getHeroById
        this.mvc.perform(get("/api/heroes/20").accept(MediaType.APPLICATION_JSON))
                // check result
                .andExpect(status().is5xxServerError());

    }
}

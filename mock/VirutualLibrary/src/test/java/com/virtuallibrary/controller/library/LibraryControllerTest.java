package com.virtuallibrary.controller.library;

import com.virtuallibrary.model.library.Library;
import com.virtuallibrary.service.library.interf.LibraryService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LibraryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService mockLibraryService;

    // List<Library> getAllLibraries()

    @Test
    public void testGetLibraryByID_UTCID01() throws Exception {
        Date date = new Date();
        given(this.mockLibraryService.findOne(1L)).willReturn(new Library(1L, "Library admin", date, true));

        this.mvc.perform(get("/api/libraries/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.libAdmin", is("Library admin")))
                .andExpect(jsonPath("$.createDate", is(date.getTime())))
                .andExpect(jsonPath("$.active", is(true)));
    }

    @Test
    public void testGetLibraryByID_UTCID02() throws Exception {
        given(this.mockLibraryService.findOne(1L)).willReturn(null);

        this.mvc.perform(get("/api/libraries/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.libAdmin").doesNotExist())
                .andExpect(jsonPath("$.createDate").doesNotExist())
                .andExpect(jsonPath("$.active").doesNotExist());
    }

    @Test
    public void testGetLibraryByID_UTCID03() throws Exception {
        given(this.mockLibraryService.findOne(1L)).willReturn(new Library());

        this.mvc.perform(get("/api/libraries/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(nullValue())))
                .andExpect(jsonPath("$.libAdmin", is(nullValue())))
                .andExpect(jsonPath("$.createDate", is(nullValue())))
                .andExpect(jsonPath("$.active", is(nullValue())));
    }

    @Test(expected = NestedServletException.class)
    public void testGetLibraryByID_UTCID04() throws Exception {
        given(this.mockLibraryService.findOne(1L)).willThrow(new NullInsteadOfMockException("Library not exists"));

        this.mvc.perform(get("/api/libraries/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    // void deleteLibraryById(@PathVariable Long id)

    @Test
    public void testDeleteLibraryByID_UTCID01() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/api/libraries/1")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());

    }

    @Test(expected = AssertionError.class)
    public void testDeleteLibraryByID_UTCID04() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/api/libraries/1")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }

    // Library updateLibrary(@RequestBody Library library)

    @Test
    public void testUpdateLibrary_UTCID01() throws Exception {
        Date date = new Date();
        Library library = new Library(1L, "Library admin", date, true);
        given(this.mockLibraryService.save(any(Library.class))).willReturn(library);

        this.mvc.perform(MockMvcRequestBuilders.put("/api/libraries/1")
                .contentType(MediaType.APPLICATION_JSON).
                        content("{\"id\":1,\"libAdmin\":\"Library admin\",\"createDate\":1491559475986,\"active\":true}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.libAdmin", is("Library admin")))
                .andExpect(jsonPath("$.createDate", is(date.getTime())))
                .andExpect(jsonPath("$.active", is(true)));
    }

    @Test
    public void testUpdateLibrary_UTCID02() throws Exception {
        given(this.mockLibraryService.save(any(Library.class))).willReturn(null);

        this.mvc.perform(MockMvcRequestBuilders.put("/api/libraries/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"libAdmin\":\"Library admin\",\"createDate\":1491559475986,\"active\":true}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.libAdmin").doesNotExist())
                .andExpect(jsonPath("$.createDate").doesNotExist())
                .andExpect(jsonPath("$.active").doesNotExist());
    }

    @Test
    public void testUpdateLibrary_UTCID03() throws Exception {
        given(this.mockLibraryService.save(any(Library.class))).willReturn(new Library());

        this.mvc.perform(MockMvcRequestBuilders.put("/api/libraries/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"libAdmin\":\"Library admin\",\"createDate\":1491559475986,\"active\":true}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(nullValue())))
                .andExpect(jsonPath("$.libAdmin", is(nullValue())))
                .andExpect(jsonPath("$.createDate", is(nullValue())))
                .andExpect(jsonPath("$.active", is(nullValue())));
    }

    @Test(expected = NestedServletException.class)
    public void testUpdateLibrary_UTCID04() throws Exception {
        given(this.mockLibraryService.save(any(Library.class))).willThrow(new NullInsteadOfMockException("Library not exists"));

        this.mvc.perform(MockMvcRequestBuilders.put("/api/libraries/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"libAdmin\":\"Library admin\",\"createDate\":1491559475986,\"active\":true}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    // Library createLibrary(@RequestBody Library library)

    @Test
    public void testCreateLibrary_UTCID01() throws Exception {
        Date date = new Date();
        Library library = new Library(1L, "Library admin", date, true);
        given(this.mockLibraryService.save(any(Library.class))).willReturn(library);

        this.mvc.perform(MockMvcRequestBuilders.post("/api/libraries")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"libAdmin\":\"Library admin\",\"createDate\":1491559475986,\"active\":true}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.libAdmin", is("Library admin")))
                .andExpect(jsonPath("$.createDate", is(date.getTime())))
                .andExpect(jsonPath("$.active", is(true)));
    }

    @Test
    public void testCreateLibrary_UTCID02() throws Exception {
        given(this.mockLibraryService.save(any(Library.class))).willReturn(null);

        this.mvc.perform(MockMvcRequestBuilders.post("/api/libraries")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"libAdmin\":\"Library admin\",\"createDate\":1491559475986,\"active\":true}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.libAdmin").doesNotExist())
                .andExpect(jsonPath("$.createDate").doesNotExist())
                .andExpect(jsonPath("$.active").doesNotExist());
    }

    @Test
    public void testCreateLibrary_UTCID03() throws Exception {
        given(this.mockLibraryService.save(any(Library.class))).willReturn(new Library());

        this.mvc.perform(MockMvcRequestBuilders.post("/api/libraries")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"libAdmin\":\"Library admin\",\"createDate\":1491559475986,\"active\":true}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(nullValue())))
                .andExpect(jsonPath("$.libAdmin", is(nullValue())))
                .andExpect(jsonPath("$.createDate", is(nullValue())))
                .andExpect(jsonPath("$.active", is(nullValue())));
    }

    @Test(expected = NestedServletException.class)
    public void testCreateLibrary_UTCID04() throws Exception {
        given(this.mockLibraryService.save(any(Library.class))).willThrow(new NullInsteadOfMockException("Library not exists"));

        this.mvc.perform(MockMvcRequestBuilders.post("/api/libraries")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"libAdmin\":\"Library admin\",\"createDate\":1491559475986,\"active\":true}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }
}

package com.virtuallibrary.controller.book;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtuallibrary.model.book.Book;
import com.virtuallibrary.service.book.interf.BookService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private BookService mockBookService;

	List<Book> listBook = Arrays.asList(new Book(1L, "Cleand Code", 150), new Book(2L, "Ky Thuat lap trinh", 250));

	Book book = new Book(1L, "Cleand Code", 150);

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, clazz);
	}

	@Test
	public void getAllBook() throws Exception {

		given(this.mockBookService.findAll()).willReturn(listBook);

		MvcResult result = this.mvc.perform(get("/api/books").accept(MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();

		int status = result.getResponse().getStatus();

		Assert.assertEquals("failure - expected HTTP status", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);
		List<Book> booksRespone = Arrays.asList(mapFromJson(content, Book[].class));
		Assert.assertEquals("failure - size book ", 2, booksRespone.size());
		Assert.assertEquals("failure - id book 1", listBook.get(0).getId(), booksRespone.get(0).getId());
		Assert.assertEquals("failure - name book 1", listBook.get(0).getName(), booksRespone.get(0).getName());
		Assert.assertEquals("failure - price book 1", listBook.get(0).getPrice(), booksRespone.get(0).getPrice());
		Assert.assertEquals("failure - id book 2", listBook.get(1).getId(), booksRespone.get(1).getId());
		Assert.assertEquals("failure - name book 2", listBook.get(1).getName(), booksRespone.get(1).getName());
		Assert.assertEquals("failure - price book 2", listBook.get(0).getPrice(), booksRespone.get(0).getPrice());
	}

	@Test
	public void getFindOneBook() throws Exception {

		Long id = new Long(1);

		when(mockBookService.findOne(id)).thenReturn(book);

		String uri = "/api/books/{id}";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);
		Book bookRespone = mapFromJson(content, Book.class);

		Assert.assertEquals("failure - id book", book.getId(), bookRespone.getId());
		Assert.assertEquals("failure - name book", book.getName(), bookRespone.getName());
		Assert.assertEquals("failure - price book", book.getPrice(), bookRespone.getPrice());
	}

	@Test
	public void testGetBookNotFound() throws Exception {
		Long id = Long.MAX_VALUE;

		when(mockBookService.findOne(id)).thenReturn(null);

		String uri = "/api/books/{id}";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		Assert.assertEquals("failure - expected HTTP status 404", 404, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() == 0);
	}

	@Test
	public void testCreateBook() throws Exception {

		when(mockBookService.save(any(Book.class))).thenReturn(book);

		String uri = "/api/books";
		String inputJson = mapToJson(book);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		Assert.assertEquals("failure - expected HTTP status 201", 201, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);
		Book bookRespone = mapFromJson(content, Book.class);
		Assert.assertNotNull("failure - expected book not null", bookRespone);
		Assert.assertEquals("failure - id book", book.getId(), bookRespone.getId());
		Assert.assertEquals("failure - name book", book.getName(), bookRespone.getName());
		Assert.assertEquals("failure - price book", book.getPrice(), bookRespone.getPrice());
	}

	@Test
	public void testUpdateBook() throws Exception {

		when(mockBookService.update(any(Book.class))).thenReturn(book);
		Long id = new Long(1);
		String uri = "/api/books/{id}";
		String inputJson = mapToJson(book);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri, id).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		verify(mockBookService, times(1)).update(any(Book.class));

		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim().length() > 0);
		Book bookRespone = mapFromJson(content, Book.class);

		Assert.assertNotNull("failure - expected book not null", bookRespone);
		Assert.assertEquals("failure - id book", book.getId(), bookRespone.getId());
		Assert.assertEquals("failure - name book", book.getName(), bookRespone.getName());
		Assert.assertEquals("failure - price book", book.getPrice(), bookRespone.getPrice());

	}

	@Test
	public void testDeleteBook() throws Exception {
		Long id = new Long(1);
		String uri = "/api/books/{id}";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri, id)).andReturn();
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		 verify(mockBookService, times(1)).delete(id);
		 Assert.assertEquals("failure - expected HTTP status 204", 204, status);
	     Assert.assertTrue("failure - expected HTTP response body to be empty",
	                content.trim().length() == 0);
	}
}

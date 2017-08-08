package com.virtuallibrary.controller.book;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.virtuallibrary.controller.BaseController;
import com.virtuallibrary.model.book.Book;
import com.virtuallibrary.service.book.interf.BookService;



@RestController
@RequestMapping("/api/books")
public class BookController extends BaseController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(method = RequestMethod.GET)

	public ResponseEntity<List<Book>> getAllBookes() {
		List<Book> books = bookService.findAll();
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)

	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		Book book = bookService.findOne(id);
		if (book == null) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}



	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		Book bookSave =  bookService.save(book);
		return new ResponseEntity<Book>(bookSave, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBook(@RequestBody Book book) {
		Book bookSave =  bookService.update(book);
		if (bookSave == null) {
			return new ResponseEntity<Book>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Book>(bookSave, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteBookById(@PathVariable Long id) {
		bookService.delete(id);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}
}

package com.virtuallibrary.service.book.interf;

import com.virtuallibrary.model.book.Book;
import com.virtuallibrary.service.IBaseService;

import java.util.List;

public interface BookService extends IBaseService<Book, Long> {
	List<Book> findByName(String name);
	List<Book> findByNameContaining(String name);
	Book update(Book bookUpdate);
}

package com.virtuallibrary.service.book;
import javax.persistence.NoResultException;
import com.virtuallibrary.model.book.Book;
import com.virtuallibrary.repository.book.interf.BookRepository;
import com.virtuallibrary.service.BaseService;
import com.virtuallibrary.service.book.interf.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service

public class BookServiceImpl extends BaseService<Book, Long> implements BookService {
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public Book findOne(Long id) {
		return bookRepository.findOne(id);
	}

	@Override
	public List<Book> findAll() {
		return (List<Book>) bookRepository.findAll();
	};

	@Override
	public List<Book> findAll(List<Long> listId) {
		return (List<Book>) bookRepository.findAll(listId);
	}

	@Override
	public long count() {
		return bookRepository.count();
	}

	@Override
	public boolean exists(Long id) {
		return bookRepository.exists(id);
	}

	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> save(List<Book> listBook) {
		return ( List<Book>) bookRepository.save(listBook);
	}

	@Override
	public void delete(Long id) {
		this.bookRepository.delete(id);
	}

	@Override
	public void delete(Book entity) {
		this.bookRepository.delete(entity);
	}

	@Override
	public void delete(List<Book> listEntity) {
		bookRepository.delete(listEntity);
	}

	@Override
	public void deleteAll() {
		bookRepository.deleteAll();
	}

	@Override
	public List<Book> findByName(String name) {
		return bookRepository.findByName(name);
	}
	
	
	public Book update(Book book) {
		Book bookUpdate = bookRepository.findOne(book.getId());
		if(bookUpdate == null) {
			 throw new NoResultException("book not found.");
		}
		bookUpdate.setName(book.getName());
		bookUpdate.setPrice(book.getPrice());
		bookRepository.save(bookUpdate);
		return bookUpdate;
	}

	@Override
	public List<Book> findByNameContaining(String name) {
		return bookRepository.findByNameContaining(name);
	}
}

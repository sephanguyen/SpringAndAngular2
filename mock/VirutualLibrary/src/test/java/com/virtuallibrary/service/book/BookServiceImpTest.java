package com.virtuallibrary.service.book;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.virtuallibrary.model.book.Book;
import com.virtuallibrary.repository.book.interf.BookRepository;
import com.virtuallibrary.service.book.interf.BookService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceImpTest {
	@MockBean
	private BookRepository mockBookRepository;
	
	@Autowired
	private BookService bookService;
	
	@Test
	public void TestFindAll() {
		  List<Book> listBook = Arrays.asList(
                new Book(1L, "Cleand Code", 150),
                new Book(2L, "Ky Thuat lap trinh", 250));
		  
		  given(this.mockBookRepository.findAll()).willReturn(listBook);
		  List<Book> books = bookService.findAll();
		  Assert.assertNotNull("failure - expected not null", books);
	      Assert.assertEquals("failure - expected list size", 2, books.size());
	      Assert.assertEquals("failure - expected get id", listBook.get(0).getId() ,books.get(0).getId());
	      Assert.assertEquals("failure - expected get id", listBook.get(0).getName() ,books.get(0).getName());
	      Assert.assertEquals("failure - expected get id", listBook.get(1).getId() ,books.get(1).getId());
	      Assert.assertEquals("failure - expected get id", listBook.get(1).getName() ,books.get(1).getName());
	  }
	
	 @Test
	  public void TestFind() {
		  List<Book> listBook = Arrays.asList(
                 new Book(1L, "Cleand Code", 150),
                 new Book(3L, "Lap Trinh Ung Dung", 250));
		  List<Long> listId = Arrays.asList(new Long(1L),
					new Long(3L));
		  given(mockBookRepository.findAll(listId)).willReturn(listBook);
		 
		  List<Book> books = bookService.findAll(listId);
		  Assert.assertNotNull("failure - expected not null", books);
	      Assert.assertEquals("failure - expected list size", 2, books.size());
	      Assert.assertEquals("failure - expected get id 1", listId.get(0) ,books.get(0).getId());
	      Assert.assertEquals("failure - expected get id 2", listId.get(1) ,books.get(1).getId());
	  }
	
	  @Test
	  public void TestFindOne() {
		  Book book = new Book(1L, "ABC", 150);
		 
		  given(mockBookRepository.findOne(1L)).willReturn(book);
		 
		  Book findBook = bookService.findOne(1L);
		  Assert.assertNotNull("failure - expected not null", findBook);
	      Assert.assertEquals("failure - expected get id ", book.getId() ,findBook.getId());
	      Assert.assertEquals("failure - expected get name", book.getName() ,findBook.getName());
	  }
	  
	  @Test
	  public void TestNotFindOne() {
		  Book findBook = bookService.findOne(12L);
		  Assert.assertNull("failure - expected null", findBook);
	  }
	  
	  @Test
	  public void TestCount() {
		  long count = 5;
		  given(mockBookRepository.count()).willReturn(count);
		  long countBook = bookService.count();
		  Assert.assertNotNull("failure - expected not null", countBook);
		  Assert.assertEquals("failure - expected count", count ,countBook);
	  }
	  
	  @Test
	  public void TestExistsBook() {
		  boolean result = true;
		  given(mockBookRepository.exists(1L)).willReturn(result);
		  boolean isExistsBook = bookService.exists(1L);
		  Assert.assertNotNull("failure - expected exists null", isExistsBook);
		  Assert.assertEquals("failure - expected exists", result ,isExistsBook);
	  }
	  
	  @Test
	  public void TestNotExistBook() {
		 
		  boolean isExistsBook = bookService.exists(2L);
		  Assert.assertNotNull("failure - expected exists null", isExistsBook);
		  Assert.assertEquals("failure - expected count", false ,isExistsBook);
	  }
	  
	  @Test
	  public void TestSaveBook() {
		  Book book = new Book(1L, "ABC", 150);
			 
		  given(mockBookRepository.save(book)).willReturn(book);
		  
		  Book bookSave = bookService.save(book);
		  Assert.assertNotNull("failure - expected book null", bookSave);
		  Assert.assertEquals("failure - expected book id", book.getId() ,bookSave.getId());
		  Assert.assertEquals("failure - expected book id", book.getName() ,bookSave.getName());
	  }
	  
	  @Test
	  public void TestSaveListBook() {
		  List<Book> listBook = Arrays.asList(
	                new Book(1L, "Cleand Code", 150),
	                new Book(2L, "Ky Thuat lap trinh", 250));
			  
			 
		  given(mockBookRepository.save(listBook)).willReturn(listBook);
		  
		  List<Book> listBookSave = bookService.save(listBook);
		  Assert.assertNotNull("failure - expected book null", listBookSave);
		  Assert.assertEquals("failure - expected book size", listBook.size(), listBookSave.size());
		  Assert.assertEquals("failure - expected get id", listBook.get(0).getId() ,listBookSave.get(0).getId());
	      Assert.assertEquals("failure - expected get id", listBook.get(0).getName() ,listBookSave.get(0).getName());
	      Assert.assertEquals("failure - expected get id", listBook.get(1).getId() ,listBookSave.get(1).getId());
	      Assert.assertEquals("failure - expected get id", listBook.get(1).getName() ,listBookSave.get(1).getName());
	  }
	  
	  @Test
	  public void TestFindByName() {
		  List<Book> listBook = Arrays.asList(
	                new Book(1L, "Ky Thuat Code", 150),
	                new Book(2L, "Ky Thuat lap trinh", 250));
			 
		  given(mockBookRepository.findByName("Ky Thuat")).willReturn(listBook);
		  
		  List<Book> listBookSave = bookService.findByName("Ky Thuat");
		  Assert.assertNotNull("failure - expected book null", listBookSave);
		  Assert.assertEquals("failure - expected book size", listBook.size(), listBookSave.size());
		  Assert.assertEquals("failure - expected get id", listBook.get(0).getId() ,listBookSave.get(0).getId());
	      Assert.assertEquals("failure - expected get id", listBook.get(0).getName() ,listBookSave.get(0).getName());
	      Assert.assertEquals("failure - expected get id", listBook.get(1).getId() ,listBookSave.get(1).getId());
	      Assert.assertEquals("failure - expected get id", listBook.get(1).getName() ,listBookSave.get(1).getName());
	  }
	  
	  @Test
	  public void TestNotFindByName() {
		  
		  List<Book> listBookSave = bookService.findByName("Ky Thuat");
		  Assert.assertNotNull("failure - expected book null", listBookSave);
		  Assert.assertEquals("failure - expected book size", 0, listBookSave.size());

	  }
	  @Test
	  public void TestUpdate() {
		  Long bookId = 1L;
		  Book book = new Book(1L, "ABC", 150);
			 
		  given(mockBookRepository.findOne(bookId)).willReturn(book);
		  
		  Book Bookupdate = new Book(1L, "Lap Trinh Nhung", 180);
		  
		  Book BookUpdated = bookService.update(Bookupdate);
		  
		  Assert.assertNotNull("failure - expected not null", BookUpdated);
	      Assert.assertEquals("failure - expected get id ", bookId , BookUpdated.getId());
	      Assert.assertEquals("failure - expected get name updated",  Bookupdate.getName(), Bookupdate.getName());
	      Assert.assertEquals("failure - expected get price updated",  Bookupdate.getPrice(), Bookupdate.getPrice());
	      
		  
	  }
	  
	  
	  @Test(expected=NoResultException.class)
	  public void TestNotUpdate() {
		  Long bookId = 1L;
		  Book book = new Book(1L, "ABC", 150);
			 
		  given(mockBookRepository.findOne(bookId)).willReturn(book);
		  
		  Book Bookupdate = new Book(2L, "Lap Trinh Nhung", 180);
		  
		  when(bookService.update(Bookupdate)).thenThrow(new NoResultException());
		  
		  
	      
		  
	  }
	  
}

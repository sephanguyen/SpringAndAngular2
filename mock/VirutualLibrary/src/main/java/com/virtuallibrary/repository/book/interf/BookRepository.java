package com.virtuallibrary.repository.book.interf;

import com.virtuallibrary.model.book.Book;
import com.virtuallibrary.repository.book.interf.BookRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
    List<Book> findByName(String name);
    List<Book> findByNameContaining(String name);
}

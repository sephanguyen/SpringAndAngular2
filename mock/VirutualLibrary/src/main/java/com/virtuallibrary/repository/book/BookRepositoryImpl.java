package com.virtuallibrary.repository.book;

import com.virtuallibrary.model.book.Book;
import com.virtuallibrary.repository.BaseRepository;
import com.virtuallibrary.repository.book.interf.BookRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl extends BaseRepository<Book, Long> implements BookRepositoryCustom {
}

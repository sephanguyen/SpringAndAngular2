package com.virtuallibrary.controller.book;

import com.virtuallibrary.model.book.Book;
import com.virtuallibrary.service.book.interf.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lequan on 2017/02/28.
 */
@RestController
@RequestMapping("/app/books")
public class BookSearchController
{
    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> findBookByName(@RequestParam String name) {
        return bookService.findByNameContaining(name);
    }
}

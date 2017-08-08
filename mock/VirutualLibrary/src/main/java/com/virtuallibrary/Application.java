package com.virtuallibrary;

import com.virtuallibrary.model.example.Hero;
<<<<<<< .mine
import com.virtuallibrary.service.book.interf.BookService;
||||||| .r232
=======
import com.virtuallibrary.model.library.Library;
import com.virtuallibrary.model.user.User;
>>>>>>> .r247
import com.virtuallibrary.service.example.interf.HeroService;
import com.virtuallibrary.service.library.interf.LibraryService;
import com.virtuallibrary.model.book.Book;
import com.virtuallibrary.service.user.interf.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean

    public CommandLineRunner demo(HeroService heroService, UserService userService, LibraryService libraryService, BookService bookService) {

        return (args) -> {
            List<Hero> heroList = Arrays.asList(
                    new Hero(1L, "Hero 1"),
                    new Hero(2L, "Hero 2"),
                    new Hero(3L, "Hero 3"),
                    new Hero(4L, "Hero 4"),
                    new Hero(5L, "Hero 5"),
                    new Hero(6L, "Hero 6"),
                    new Hero(7L, "Hero 7"),
                    new Hero(8L, "Hero 8"),
                    new Hero(9L, "Hero 9"),
                    new Hero(10L, "Hero 10"));
            heroService.save(heroList);

            List<User> userList = Arrays.asList(
                    new User(1L, "User 1","AB","12","user1","1",
                    		"User",new Date(0),"TPHCM","09xx","xx@gmail.com",
                    		"039xx",true),
                    new User(2L, "User 2","CD","43","user2","2",
                    		"User",new Date(0),"HN","09xx","xx@gmail.com",
                    		"039xx",true));
            userService.save(userList);
            
            List<Book> listBook = Arrays.asList(
                    new Book(1L, "Cleand Code", 150),
                    new Book(2L, "Ky Thuat lap trinh", 250));
                    
            bookService.save(listBook);

            List<Library> libraryList = Arrays.asList(
                    new Library(1L, "Library 1", new Date(), true),
                    new Library(2L, "Library 2", new Date(), false),
                    new Library(3L, "Library 3", new Date(), false),
                    new Library(4L, "Library 4", new Date(), true),
                    new Library(5L, "Library 5", new Date(), false));
            libraryService.save(libraryList);

        };
    }
}

package com.book.store;

import com.book.store.model.Book;
import com.book.store.service.BookService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            Book book = new Book();
            book.setAuthor("Nick");
            book.setIsbn("123");
            book.setDescription("desc");
            book.setCoverImage("url");
            book.setPrice(BigDecimal.valueOf(15));
            book.setTitle("title");

            bookService.save(book);
            List<Book> allBooks = bookService.findAll();

            System.out.println(allBooks.size());
        };
    }
}

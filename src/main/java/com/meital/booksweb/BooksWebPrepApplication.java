package com.meital.booksweb;

import com.meital.booksweb.errors.exceptions.LibraryCustomException;
import com.meital.booksweb.model.Author;
import com.meital.booksweb.model.Book;
import com.meital.booksweb.repo.AuthorRepo;
import com.meital.booksweb.repo.BookRepo;
import com.meital.booksweb.service.LibraryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BooksWebPrepApplication {

    public static void main(String[] args) throws LibraryCustomException {
        ApplicationContext ctx = SpringApplication.run(BooksWebPrepApplication.class, args);

       








    }



}

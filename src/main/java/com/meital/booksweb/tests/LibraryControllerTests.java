package com.meital.booksweb.tests;

import com.meital.booksweb.model.Author;
import com.meital.booksweb.model.Book;
import com.meital.booksweb.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
@RequiredArgsConstructor
@Order(2)
public class LibraryControllerTests implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private final BookRepo bookRepo;

    @Override
    public void run(String... args) throws Exception {
        testGetAuthorById();
        testGetAllBooks();
        testGetBooksBetweenYear();
        testBooksAvgYear();
        testBooksAvgYearByAuthor();
        testCreateAuthor();
        testDeleteAuthor();


    }


    private void testGetAuthorById() {
        final ResponseEntity<Author> authorRes = restTemplate.exchange("http://localhost:8080/library/author/2", HttpMethod.GET, null, Author.class);
        System.out.println("\033[0;33m" + "Author: " + authorRes.getBody() + "\033[0m");
    }

    private void testGetAllBooks() {
        ResponseEntity<Book[]> response =
                restTemplate.getForEntity(
                        "http://localhost:8080/library/allBooks",
                        Book[].class);
        Book[] books = response.getBody();
        if (books != null) {
            System.out.println("\033[0;33m" + "All Books: " + Arrays.toString(books) + "\033[0m");
        }
    }

    private void testGetBooksBetweenYear() {
        ResponseEntity<Book[]> response =
                restTemplate.getForEntity(
                        "http://localhost:8080/library/books/1980/2005",
                        Book[].class);
        Book[] books = response.getBody();
        if (books != null) {
            System.out.println("\033[0;33m" + "All Books between 1980 - 2005: " + Arrays.toString(books) + "\033[0m");
        }
    }

    private void testBooksAvgYear() {
        ResponseEntity<Double> result = restTemplate.exchange
                ("http://localhost:8080/library/books_avg_year",
                        HttpMethod.GET, null, Double.class);
        System.out.println("\033[0;33m" + "Books average year: " + result.getBody() + "\033[0m");
    }


    private void testBooksAvgYearByAuthor() {
        ResponseEntity<Double> result = restTemplate.exchange
                ("http://localhost:8080/library/book_avg_year_by_author/2",
                        HttpMethod.GET, null, Double.class);
        System.out.println("\033[0;33m" + "Books average year for author id 2: " + result.getBody() + "\033[0m");
    }

    private void testCreateAuthor() {
        Book book5 = bookRepo.save(Book.builder().name("book5").year(2006).build());
        Book book6 = bookRepo.save(Book.builder().name("book6").year(1999).build());

        //Book lists
        List<Book> books = new ArrayList<>();
        books.add(book5);
        books.add(book6);

        Author author = Author.builder().name("author4").books(books).build();
        ResponseEntity<Author> result = restTemplate.postForEntity("http://localhost:8080/library/new_author", author, Author.class);
        System.out.println("\033[0;33m" + result.getStatusCode() + "\033[0m");
    }

    private void testDeleteAuthor() {
        ResponseEntity<Author> s = restTemplate.exchange("http://localhost:8080/library/author/3", HttpMethod.DELETE, null, Author.class);
        System.out.println("\033[0;33m" + s.getStatusCode() + "\033[0m");
    }

}

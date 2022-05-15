package com.meital.booksweb.tests;

import com.meital.booksweb.errors.exceptions.LibraryCustomException;
import com.meital.booksweb.model.Author;
import com.meital.booksweb.model.Book;
import com.meital.booksweb.repo.AuthorRepo;
import com.meital.booksweb.repo.BookRepo;
import com.meital.booksweb.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Order(1)
public class LibraryServiceTests implements CommandLineRunner {

    private final LibraryService libraryService;
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    @Override
    public void run(String... args) throws Exception {

        testCreateAuthor();

        testDeleteAuthorById();

        testGetAllBooks();

        testGetAuthorDetails();

        testGetAllBooksBetweenYears();

        testBookAvgYear();

        testBookAvgYearByAuthorId();


    }

    private void testCreateAuthor() throws LibraryCustomException {
        //books
        Book book = bookRepo.save(Book.builder().name("book").year(2020).build());
        Book book1 = bookRepo.save(Book.builder().name("book1").year(2002).build());

        //Book lists
        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book1);

        Author author = libraryService.createAuthor(Author.builder().name("author1").books(books).build());
        if (author.getId() == 1) {
            System.out.println("\033[0;32m" + "Test create Author succeeded" + "\033[0m");
        } else {
            throw new LibraryCustomException("Test Failed");
        }

    }

    private void testDeleteAuthorById() throws LibraryCustomException {
        Author author = authorRepo.getById(1);
        libraryService.deleteAuthor(author.getId());

        if (!authorRepo.existsById(1)) {
            System.out.println("\033[0;32m" + "Test delete Author succeeded" + "\033[0m");
        } else {
            throw new LibraryCustomException("Test Failed");
        }
    }

    private void testGetAllBooks() throws LibraryCustomException {
        List<Book> books = libraryService.getAllBooks();
        if (books.isEmpty()) {
            throw new LibraryCustomException("Test Failed");
        } else {
            System.out.println("\033[0;32m" + "Test get all books succeeded" + "\033[0m");
        }
    }

    public void testGetAuthorDetails() throws LibraryCustomException {
        Book book = bookRepo.getById(1);
        Book book1 = bookRepo.getById(2);

        //Book lists
        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book1);

        Author author = libraryService.createAuthor(Author.builder().name("author1").books(books).build());
        Optional<Author> authorDetails = libraryService.getAuthor(author.getId());
        if (authorDetails.isPresent()) {
            System.out.println("\033[0;32m" + "Test get Author succeeded" + "\033[0m");
        } else {
            throw new LibraryCustomException("Test Failed");
        }
    }

    public void testGetAllBooksBetweenYears() throws LibraryCustomException {
        Book book2 = bookRepo.save(Book.builder().name("book2").year(1980).build());
        Book book3 = bookRepo.save(Book.builder().name("book3").year(2005).build());

        List<Book> books = libraryService.getAllBooksBetweenYears(1990,2005);
        if (books.contains(book3)) {
            System.out.println("\033[0;32m" + "Test get books between years succeeded" + "\033[0m");
        } else {
            throw new LibraryCustomException("Test Failed");
        }
    }

    public void testBookAvgYear() throws LibraryCustomException {
        double booksAvgYear = libraryService.booksAvgYear();
        if (booksAvgYear == 2001.75){
            System.out.println("\033[0;32m" + "Test get books avg year succeeded" + "\033[0m");
        } else {
            throw new LibraryCustomException("Test Failed");
        }
    }

    public void testBookAvgYearByAuthorId () throws LibraryCustomException {

        double bookAvgYearByAuthor = libraryService.booksAvgYearByAuthorId(2);
        if (bookAvgYearByAuthor == 2011) {
            System.out.println("\033[0;32m" + "Test get books avg year by author Id succeeded" + "\033[0m");
        } else {
            throw new LibraryCustomException("Test Failed");
        }
    }

}

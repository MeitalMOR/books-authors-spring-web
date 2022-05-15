package com.meital.booksweb.controller;

import com.meital.booksweb.errors.exceptions.LibraryCustomException;
import com.meital.booksweb.model.Author;
import com.meital.booksweb.model.Book;
import com.meital.booksweb.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(path = "library")
@RestController
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping(path = "/author/{id}")
    public Optional<Author> getAuthor(@PathVariable("id") Integer id) throws LibraryCustomException {
        return libraryService.getAuthor(id);
    }

    @DeleteMapping(path = "author/{id}")
    public void deleteAuthor(@PathVariable("id") Integer id) throws LibraryCustomException {
        libraryService.deleteAuthor(id);
        System.out.println("Author " + id + " deleted");
    }

    @GetMapping(path = "allBooks")
    public List<Book> getAllBooks() throws LibraryCustomException {
        return libraryService.getAllBooks();
    }

    @GetMapping(path = "books/{startYear}/{endYear}")
    @ResponseBody
    public List<Book> getBookBetweenYears
            (@PathVariable Integer startYear, @PathVariable Integer endYear) throws LibraryCustomException {

        return libraryService.getAllBooksBetweenYears(startYear, endYear);
    }

    @GetMapping(path = "books_avg_year")
    public double booksAvgYear() throws LibraryCustomException {
        return libraryService.booksAvgYear();
    }

    @GetMapping(path = "book_avg_year_by_author/{id}")
    public double getBookYearAvgByAuthor(@PathVariable("id") Integer id) throws LibraryCustomException {
        return libraryService.booksAvgYearByAuthorId(id);
    }

    @PostMapping(path = "new_author")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createAuthor(@RequestBody Author author) throws LibraryCustomException {
        Author author1 = libraryService.createAuthor(author);
        System.out.println("Author " + author1.getId() + " created");
    }




}

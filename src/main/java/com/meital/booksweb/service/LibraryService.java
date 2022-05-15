package com.meital.booksweb.service;

import com.meital.booksweb.errors.exceptions.LibraryCustomException;
import com.meital.booksweb.model.Author;
import com.meital.booksweb.model.Book;
import com.meital.booksweb.repo.AuthorRepo;
import com.meital.booksweb.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;

    //create new author
    public Author createAuthor(Author author) throws LibraryCustomException {

        if (author.getBooks().isEmpty()) {
            throw new LibraryCustomException("Add books to author");
        }
        return authorRepo.save(author);
    }

    //delete author by id
    public void deleteAuthor(int authorId) throws LibraryCustomException {

        if (authorRepo.existsById(authorId)) {
            authorRepo.deleteById(authorId);
        } else {
            throw new LibraryCustomException("ID doesn't exist");
        }
    }

    //get all books
    public List<Book> getAllBooks() throws LibraryCustomException {
        if (bookRepo.findAll().isEmpty()) {
            throw new LibraryCustomException("there are no books");
        }
        return bookRepo.findAll();
    }

    //get author details
    public Optional<Author> getAuthor(int authorId) throws LibraryCustomException {
        Optional<Author> authorOpt = authorRepo.findById(authorId);
        if (authorOpt.isEmpty()) {
            throw new LibraryCustomException("ID doesn't exist");
        }
        return authorOpt;
    }

    //list of books between years
    public List<Book> getAllBooksBetweenYears(int startYear, int endYear) throws LibraryCustomException {

        if (startYear < endYear) {
            return bookRepo.getAllByYearBetween(startYear, endYear);
        } else {
            throw new LibraryCustomException("start year is after end year");
        }
    }

    //average year of all books
    public double booksAvgYear() throws LibraryCustomException {
        if (bookRepo.findAll().isEmpty()) {
            throw new LibraryCustomException("there are no books");
        }
        return bookRepo.avg();
    }

    //average year of books by author id
    public double booksAvgYearByAuthorId(int authorId) throws LibraryCustomException {
        Optional<Author> authorOpt = authorRepo.findById(authorId);
        if (authorOpt.isEmpty()) {
            throw new LibraryCustomException("ID doesn't exist");
        } else {
            List<Book> books = authorOpt.get().getBooks();
            double sum = 0;
            double count = 0;
            for (Book b : books) {
                sum = sum + b.getYear();
                count++;
            }
            return sum / count;
        }
    }


}

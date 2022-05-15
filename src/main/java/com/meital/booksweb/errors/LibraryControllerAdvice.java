package com.meital.booksweb.errors;

import com.meital.booksweb.errors.exceptions.LibraryCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LibraryControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LibraryCustomException.class)
    public String libraryExceptionHandling(LibraryCustomException e) {
        return e.getMessage();
    }
}

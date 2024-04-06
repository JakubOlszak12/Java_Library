package com.example.Library.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(long id) {
        super("There is no book with id: " + id);
    }
}

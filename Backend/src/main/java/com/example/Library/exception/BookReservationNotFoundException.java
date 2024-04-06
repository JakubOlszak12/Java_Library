package com.example.Library.exception;

public class BookReservationNotFoundException extends RuntimeException{
    public BookReservationNotFoundException(long id) {
        super("There is no book reservation with id: " + id);
    }
}

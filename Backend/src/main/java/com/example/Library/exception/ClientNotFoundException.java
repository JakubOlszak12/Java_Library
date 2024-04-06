package com.example.Library.exception;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(long id) {
        super("There is no client with id: " + id);
    }
}

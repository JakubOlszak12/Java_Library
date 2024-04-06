package com.example.Library.service;

import com.example.Library.Model.Book;
import com.example.Library.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BookService {

    List<BookDto> getAllBooks();

    Page<Book> getBookPage(Pageable pageable);

    Page<BookDto> getBookDtoPage(Pageable pageable);

    Book addBook(Book book);

    Book getBookById(Long id);

    boolean deleteBookById(Long id);

    Book editBook(Book book);
}

package com.example.Library.controller;

import com.example.Library.Model.Book;
import com.example.Library.dto.BookDto;
import com.example.Library.service.BookService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    ModelMapper modelMapper;
    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getBooks() {
        List<BookDto> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(books);
        }
    }

    @GetMapping("/booksPage")
    public ResponseEntity<Page<BookDto>> getBooksPage(@PageableDefault(size = 10) Pageable pageable) {
        Page<BookDto> productsPage = bookService.getBookDtoPage(pageable);
        if (productsPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productsPage);
        }
    }
}

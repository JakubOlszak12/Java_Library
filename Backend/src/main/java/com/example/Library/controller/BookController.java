package com.example.Library.controller;

import com.example.Library.Model.Book;
import com.example.Library.Model.BookReservation;
import com.example.Library.dto.BookDto;
import com.example.Library.exception.BookNotFoundException;
import com.example.Library.service.BookReservationService;
import com.example.Library.service.BookService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BookReservationService bookReservationService;
    @GetMapping("/booksList")
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


    @PostMapping("/save")
    public ResponseEntity<?> savePost(@Valid @RequestBody Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errors = new ArrayList<String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ": " + error.getDefaultMessage()); }
            // Return the map of errors in the response
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok().body(bookService.addBook(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){
            Book book = bookService.getBookById(id);
            if(book == null){
                throw new BookNotFoundException(id);
            }
            return ResponseEntity.ok().body(book);

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        boolean isPresent = bookService.deleteBookById(id);
        if(!isPresent){
            throw new BookNotFoundException(id);
        }else{
            if(bookReservationService.findById(id) != null){
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.badRequest().build();
            }
        }
    }
}

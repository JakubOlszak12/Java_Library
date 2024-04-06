package com.example.Library.service;

import com.example.Library.Model.Book;
import com.example.Library.Model.BookReservation;
import com.example.Library.Model.Client;
import com.example.Library.converter.BookConverter;
import com.example.Library.dto.BookDto;
import com.example.Library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

   private final BookRepository bookRepository;
   private final BookConverter bookConverter;
    private final ModelMapper modelMapper;

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Book> getBookPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Page<BookDto> getBookDtoPage(Pageable pageable) {
        return bookRepository.findAll(pageable).map((element) -> modelMapper.map(element, BookDto.class));
    }

    @Override
    public Book addBook(Book book) {
        Book newBook = new Book(null,book.getTitle(),book.getAuthor(),book.getPublisher(),book.getIsbn(),book.getDescription(),new HashSet<BookReservation>());
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Override
    public boolean deleteBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Book editBook(Book book) {
        Optional<Book> exists = bookRepository.findById(book.getId());
        if(exists.isPresent()) {
            exists.get().setAuthor(book.getAuthor());
            exists.get().setDescription(book.getDescription());
            exists.get().setIsbn(book.getIsbn());
            exists.get().setPublisher(book.getPublisher());
            exists.get().setTitle(book.getTitle());
            return bookRepository.save(exists.get());
        }
        return null;
    }

}

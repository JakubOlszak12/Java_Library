package com.example.Library.service;

import com.example.Library.Model.Book;
import com.example.Library.converter.BookConverter;
import com.example.Library.dto.BookDto;
import com.example.Library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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


}

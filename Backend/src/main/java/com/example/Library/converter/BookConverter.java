package com.example.Library.converter;

import com.example.Library.Model.Book;
import com.example.Library.dto.BookDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookConverter implements Converter<Book, BookDto> {
    @Override
    public BookDto convert(Book source){
        return BookDto.builder()
                .id(source.getId())
        .title(source.getTitle())
        .author(source.getAuthor())
        .publisher(source.getPublisher())
        .isbn(source.getIsbn())
        .description(source.getDescription())
                .build();
    }
}

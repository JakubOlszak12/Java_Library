package com.example.Library.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private long id;

    private String title;

    private String author;

    private String publisher;

    private String isbn;

    private String description;


}

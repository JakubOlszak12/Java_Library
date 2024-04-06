package com.example.Library.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Only letters and numbers are allowed")
    private String title;

    private String author;

    private String publisher;

    private String isbn;

    private String description;

    private int quantity;

    private int currentQuantity;

    @OneToMany(mappedBy = "book")
    Set<BookReservation> reservations;

}

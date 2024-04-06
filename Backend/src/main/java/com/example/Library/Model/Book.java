package com.example.Library.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Only letters and numbers are allowed")
    private String author;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Only letters and numbers are allowed")
    private String publisher;

    @NotNull
    @Pattern(regexp = "\\b(?:ISBN(?:-1[03])?:? ?)?(?=[-0-9X]{17}$|[-0-9X]{13}$|X)(?:97[89][- ]?)?[0-9]{1,5}[- ]?(?:[0-9]+[- ]?){2}[0-9X]\\b", message = "The ISBN must be 10 or 13 digits long")
    private String isbn;

    @Size(min= 10, max=300, message = "The description must be between 10 and 300 characters")
    private String description;


    @OneToMany(mappedBy = "book")
    Set<BookReservation> reservations;

}

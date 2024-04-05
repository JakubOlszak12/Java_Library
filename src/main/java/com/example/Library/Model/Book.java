package com.example.Library.Model;

import jakarta.persistence.*;
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
    private long id;

    private String title;

    private String author;

    private String publisher;

    private String isbn;

    private String description;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

}

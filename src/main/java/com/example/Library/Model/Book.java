package com.example.Library.Model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String title;
    @Column

    private String author;
    @Column
    private String publisher;
    @Column
    private String isbn;
    @Column
    private String description;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

}

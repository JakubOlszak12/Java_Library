package com.example.Library.repository;

import com.example.Library.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Book findByTitle(String title);
    Book findByAuthor(String author);

    Book findByIsbn(String isbn);

    Book findByPublisher(String publisher);
}

package com.example.Library.repository;

import com.example.Library.Model.BookReservation;
import com.example.Library.dto.BookReservationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {
    List<BookReservation> findByBookId(Long bookId);
    BookReservation findByClientId(Long client_id);
}

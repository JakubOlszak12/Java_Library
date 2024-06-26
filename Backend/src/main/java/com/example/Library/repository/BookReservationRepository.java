package com.example.Library.repository;

import com.example.Library.Model.BookReservation;
import com.example.Library.dto.BookReservationDto;
import com.example.Library.dto.ReservationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {
    List<BookReservation> findByBookId(Long bookId);
    BookReservation findByClientId(Long client_id);

    @Query("SELECT NEW com.example.Library.dto.ReservationDto(r.id, c.name, c.surname, c.email, c.id, b.title, b.id, r.startReservationDate, r.endReservationDate) " +
            "FROM Client c " +
            "JOIN c.reservations r " +
            "JOIN r.book b")
    List<ReservationDto> findAllReservations();
}

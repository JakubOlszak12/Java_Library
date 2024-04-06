package com.example.Library.service;

import com.example.Library.Model.BookReservation;
import com.example.Library.dto.BookReservationDto;
import com.example.Library.dto.ReservationDto;

import java.util.List;

public interface BookReservationService {
    BookReservationDto addBookReservation(BookReservationDto bookReservation);

    BookReservation findById(Long id);

    List<ReservationDto> getAllReservations();

    boolean deleteBookReservation(Long id);
}

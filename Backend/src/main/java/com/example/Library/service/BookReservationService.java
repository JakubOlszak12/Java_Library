package com.example.Library.service;

import com.example.Library.Model.BookReservation;
import com.example.Library.dto.BookReservationDto;

public interface BookReservationService {
    BookReservationDto addBookReservation(BookReservationDto bookReservation);

    BookReservation findById(Long id);

}

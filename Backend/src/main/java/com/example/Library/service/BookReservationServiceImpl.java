package com.example.Library.service;

import com.example.Library.Model.Book;
import com.example.Library.Model.BookReservation;
import com.example.Library.Model.Client;
import com.example.Library.dto.BookDto;
import com.example.Library.dto.BookReservationDto;
import com.example.Library.dto.ReservationDto;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.BookReservationRepository;
import com.example.Library.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookReservationServiceImpl implements BookReservationService{

    private final ClientRepository clientRepository;
    private final BookReservationRepository bookReservationRepository;
    private final BookRepository bookRepository;
    @Override
    public BookReservationDto addBookReservation(BookReservationDto bookReservation) {
        Optional<Client> client = clientRepository.findById(Long.valueOf(bookReservation.getClient_id()));
        Optional<Book> book = bookRepository.findById(Long.valueOf(bookReservation.getBook_id()));
        if(client.isPresent() && book.isPresent()) {
            LocalDateTime startReservationDate = LocalDateTime.now();
            LocalDateTime endReservationDate = startReservationDate.plusDays(14);
            BookReservation newBookReservation = new BookReservation(
                    null,
                    client.get(),
                    book.get(),
                    startReservationDate,
                    endReservationDate);
            bookReservationRepository.save(newBookReservation);
            return bookReservation;
        }
        return null;
    }

    @Override
    public BookReservation findById(Long id) {
        Optional<BookReservation> bookReservation = Optional.ofNullable(bookReservationRepository.findByClientId(id));
        return bookReservation.orElse(null);
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        return bookReservationRepository.findAllReservations();
    }

    @Override
    public boolean deleteBookReservation(Long id) {
        Optional<BookReservation> bookReservation = bookReservationRepository.findById(id);
        if(bookReservation.isPresent()) {
            bookReservationRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}

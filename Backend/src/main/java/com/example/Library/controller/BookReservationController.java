package com.example.Library.controller;

import com.example.Library.Model.Book;
import com.example.Library.Model.BookReservation;
import com.example.Library.Model.Client;
import com.example.Library.dto.BookReservationDto;
import com.example.Library.dto.ReservationDto;
import com.example.Library.exception.BookNotFoundException;
import com.example.Library.exception.BookReservationNotFoundException;
import com.example.Library.exception.ClientNotFoundException;
import com.example.Library.service.BookReservationService;
import com.example.Library.service.BookService;
import com.example.Library.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin
public class BookReservationController {

    @Autowired
    BookReservationService  bookReservationService;

    @Autowired
    BookService bookService;

    @Autowired
    ClientService clientService;
    @PostMapping("/save")
    public ResponseEntity<BookReservationDto> saveBookReservation(@RequestBody BookReservationDto bookReservation){
        Book book = bookService.getBookById(Long.valueOf(bookReservation.getBook_id()));
        if(book == null){
            throw new BookNotFoundException((Long.valueOf(bookReservation.getBook_id())));
        }
        Client client = clientService.getClientById(Long.valueOf(bookReservation.getClient_id()));
        if(client == null){
            throw new ClientNotFoundException((Long.valueOf(Long.valueOf(bookReservation.getClient_id()))));
        }
        return ResponseEntity.ok().body(bookReservationService.addBookReservation(bookReservation));
    }

    @GetMapping("/reservationList")
    public ResponseEntity<?> getBookReservations() {
        List<ReservationDto> bookReservations = bookReservationService.getAllReservations();
        if (bookReservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(bookReservations);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBookReservation(@PathVariable Long id) {
       boolean isPresent = bookReservationService.deleteBookReservation(id);
       if(!isPresent){
           throw new BookReservationNotFoundException(id);
       }
       return ResponseEntity.ok().build();
    }
}

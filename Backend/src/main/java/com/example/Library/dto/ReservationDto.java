package com.example.Library.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Long clientId;
    private String bookTitle;
    private Long bookId;
    private LocalDateTime startReservationDate;
    private LocalDateTime endReservationDate;
}

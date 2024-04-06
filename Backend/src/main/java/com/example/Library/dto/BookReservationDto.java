package com.example.Library.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookReservationDto {
    private long id;
    private int client_id;
    private int book_id;
}

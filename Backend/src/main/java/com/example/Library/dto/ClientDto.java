package com.example.Library.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
}

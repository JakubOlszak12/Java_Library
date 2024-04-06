package com.example.Library.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "^[A-Z][a-zA-Z]{0,19}$", message = "Invalid name format")
    private String name;
    @NotNull
    @Pattern(regexp = "^[A-Z][a-zA-Z]{0,19}$", message = "Invalid surname format")
    private String surname;
    @Email
    private String email;

    @OneToMany(mappedBy = "client")
    Set<BookReservation> reservations;

}

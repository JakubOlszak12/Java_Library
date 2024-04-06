package com.example.Library.repository;

import com.example.Library.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByName(String username);
    Client findByEmail(String email);
    Client findBySurname(String surname);

    Optional<Client> findById(Long id);
}

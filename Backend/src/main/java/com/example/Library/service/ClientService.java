package com.example.Library.service;


import com.example.Library.Model.Client;
import com.example.Library.dto.BookDto;
import com.example.Library.dto.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAllClients();

    Page<Client> getClientPage(Pageable pageable);

    Page<ClientDto> getClientDtoPage(Pageable pageable);

    Client addClient(Client client);

    Client getClientById(Long id);

    boolean deleteClientById(Long id);

    List<BookDto> getAllReservedBooksByUserId(Long userId);
}

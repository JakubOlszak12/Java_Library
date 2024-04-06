package com.example.Library.service;

import com.example.Library.Model.BookReservation;
import com.example.Library.Model.Client;
import com.example.Library.converter.ClientConverter;
import com.example.Library.dto.BookDto;
import com.example.Library.dto.ClientDto;
import com.example.Library.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;
    private final ModelMapper modelMapper;

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Client> getClientPage(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Page<ClientDto> getClientDtoPage(Pageable pageable) {
        return clientRepository.findAll(pageable).map((element) -> modelMapper.map(element, ClientDto.class));
    }

    @Override
    public Client addClient(Client client) {
        Client newClient = new Client(null,client.getName(),client.getSurname(),client.getEmail(),new HashSet<>());
        return clientRepository.save(newClient);
    }

    @Override
    public Client getClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    @Override
    public boolean deleteClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<BookDto> getAllReservedBooksByUserId(Long userId) {
        Optional<Client> client = clientRepository.findById(userId);
        if(client.isPresent()){
           List<BookDto> reservedBooks = new ArrayList<BookDto>();
           for(BookReservation reservation : client.get().getReservations()){
               BookDto book = modelMapper.map(reservation.getBook(), BookDto.class);
               reservedBooks.add(book);
           }
           return reservedBooks;
        }
        return null;
    }

    @Override
    public Client editClient(Client client) {
        Optional<Client> exists = clientRepository.findById(client.getId());
        if(exists.isPresent()){
            exists.get().setName(client.getName());
            exists.get().setEmail(client.getEmail());
            exists.get().setSurname(client.getSurname());
            return clientRepository.save(exists.get());
        }
        return null;
    }
}

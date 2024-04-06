package com.example.Library.controller;

import com.example.Library.Model.Book;
import com.example.Library.Model.Client;
import com.example.Library.dto.ClientDto;
import com.example.Library.exception.BookNotFoundException;
import com.example.Library.exception.ClientNotFoundException;
import com.example.Library.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin
public class ClientController {
    @Autowired
    ClientService clientService;
    @PostMapping("/save")
    public ResponseEntity<Client> saveClient(@Valid @RequestBody ClientDto client){
        return ResponseEntity.ok().body(clientService.addClient(client));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id){
        Client client = clientService.getClientById(id);
        if(client == null){
            throw new ClientNotFoundException(id);
        }
        return ResponseEntity.ok().body(client);

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        boolean isPresent = clientService.deleteClientById(id);
        if(!isPresent){
            throw new ClientNotFoundException(id);
        }
        return ResponseEntity.ok().build();
    }
}

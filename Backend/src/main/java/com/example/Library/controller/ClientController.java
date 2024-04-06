package com.example.Library.controller;

import com.example.Library.Model.Book;
import com.example.Library.Model.Client;
import com.example.Library.dto.BookDto;
import com.example.Library.dto.ClientDto;
import com.example.Library.exception.ClientNotFoundException;
import com.example.Library.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin
public class ClientController {
    @Autowired
    ClientService clientService;
    @PostMapping("/save")
    public ResponseEntity<?> saveClient(@RequestBody @Valid  Client client, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errors = new ArrayList<String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ": " + error.getDefaultMessage()); }
            // Return the map of errors in the response
            return ResponseEntity.badRequest().body(errors);
        }
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

    @GetMapping("/clientsList")
    public ResponseEntity<List<ClientDto>> getClients() {
        List<ClientDto> clients = clientService.getAllClients();
        if (clients.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(clients);
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        boolean isPresent = clientService.deleteClientById(id);
        if(!isPresent){
            throw new ClientNotFoundException(id);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDto>> getReservedBooks(@PathVariable Long id){
        Client client = clientService.getClientById(id);
        if(client == null){
            throw new ClientNotFoundException(id);
        }
        return ResponseEntity.ok().body(clientService.getAllReservedBooksByUserId(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editClient(@PathVariable Long id,@Valid @RequestBody Client client, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            List<String> errors = new ArrayList<String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ": " + error.getDefaultMessage()); }
            // Return the map of errors in the response
            return ResponseEntity.badRequest().body(errors);
        }
        Client updatedClient = clientService.editClient(client);
        if (updatedClient == null) {
            throw new ClientNotFoundException(id);
        }

        return ResponseEntity.ok(updatedClient);
    }
}

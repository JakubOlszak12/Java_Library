package com.example.Library.converter;


import com.example.Library.Model.Client;
import com.example.Library.dto.ClientDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter implements Converter<Client, ClientDto> {
    @Override
    public ClientDto convert(Client source){
        return ClientDto.builder()
                .id(source.getId())
                .name(source.getName())
                .surname(source.getSurname())
                .email(source.getEmail())
                .build();
    }
}

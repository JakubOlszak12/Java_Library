package com.example.Library.dto;

import com.example.Library.Model.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private String email;
    private List<Order> ordersList;
}

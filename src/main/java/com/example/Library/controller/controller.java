package com.example.Library.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class controller {
    @RequestMapping({ "/hello" })
    public String welcomePage() {
        return "Welcome!";
    }

}


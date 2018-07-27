package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class PageController {

    @GetMapping("/hello")
    public String SayHello()
    {
        return "Hello World";

    }
}


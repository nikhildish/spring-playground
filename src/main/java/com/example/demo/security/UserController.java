package com.example.demo.security;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    @RestController
    @RequestMapping("/employees")
    public class EmployeesController {

        @GetMapping("")
        public String getEmployees() {
            return "Super secret list of employees";
        }

    }
}

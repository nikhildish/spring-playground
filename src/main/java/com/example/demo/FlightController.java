package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private  FlightService flightService;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/flight")
    public Flight getFlight() throws Exception
    {
       // System.out.println(objectMapper.writeValueAsString(flightService.getFlights()));
        return flightService.getFlights();
    }

}


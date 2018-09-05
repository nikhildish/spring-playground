package com.example.demo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {

    @Autowired
    private  FlightService flightService;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/flights/flight")
    public Flight getFlight() throws Exception
    {
       // System.out.println(objectMapper.writeValueAsString(flightService.getFlights()));
        return flightService.getFlight();
    }

    @GetMapping("/flights")
    public List<Flight> getFlights() throws Exception
    {
        // System.out.println(objectMapper.writeValueAsString(flightService.getFlights()));
        return flightService.getFlights();
    }

   @RequestMapping(value = "/flights/tickets/total", method = {RequestMethod.POST},
            consumes = "application/json", produces = "application/json")
    public @ResponseBody Result postTotal(@RequestBody FlightTicket flightTicket) {

       Result result = new Result();
       result.setResult(flightService.getTicketTotal(flightTicket));
       return result;

    }

}



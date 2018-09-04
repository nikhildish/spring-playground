package com.example.demo;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import java.util.Arrays;

@Service

public class FlightService {

    public FlightService() {
    }
        public Flight getFlights() {
        Flight flight1 = new Flight();
        flight1.setDeparts(new Date(2017 - 1900, 04, 21,14,34));
           Flight.Tickets ticket = new Flight.Tickets();
           Flight.Tickets.Person passenger1 = new Flight.Tickets.Person();
            passenger1.setFirstName("Some name");
            passenger1.setLastName("Some other name");
            ticket.setPrice(200);
            ticket.setPassanger(Arrays.asList(passenger1));
            flight1.setTickets(Arrays.asList(ticket));

        return flight1;
}

}

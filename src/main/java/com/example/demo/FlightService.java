package com.example.demo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

import java.util.Arrays;

@Service

public class FlightService {

    public FlightService() {
    }
        public Flight getFlight() {
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


    public List<Flight> getFlights() {
        Flight flight1 = new Flight();
        flight1.setDeparts(new Date(2017 - 1900, 04, 21,14,34));
        Flight.Tickets ticket1 = new Flight.Tickets();
        Flight.Tickets.Person passenger1 = new Flight.Tickets.Person();
        passenger1.setFirstName("Some name");
        passenger1.setLastName("Some other name");
        ticket1.setPrice(200);
        ticket1.setPassanger(Arrays.asList(passenger1));
        flight1.setTickets(Arrays.asList(ticket1));

        Flight flight2 = new Flight();
        flight2.setDeparts(new Date(2017 - 1900, 04, 21,14,34));
        Flight.Tickets ticket2 = new Flight.Tickets();
        Flight.Tickets.Person passenger2 = new Flight.Tickets.Person();
        passenger2.setFirstName("Some name");
        //passenger2.setLastName("Some other name");
        ticket2.setPrice(400);
        ticket2.setPassanger(Arrays.asList(passenger2));
        flight2.setTickets(Arrays.asList(ticket2));

        return Arrays.asList(flight1,flight2);
    }

    public int getTicketTotal(FlightTicket flightTicket)
    {
        int price = 0;

        List<Details> listTickets = flightTicket.getTickets();
        for(Details val : listTickets ){
            price += val.getPrice();
        }
        return price;
    }
}

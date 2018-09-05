package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest({FlightController.class,FlightService.class})
public class FlightControllerTest {
    @Autowired
    MockMvc mvc;

 @Test
    public void testFlights() throws Exception {
        this.mvc.perform(
                get("/flights")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].Tickets[0].Passanger[0].FirstName", is("Some name")))
                .andExpect(jsonPath("$.[0].Tickets[0].Passanger[0].LastName", is("Some other name")))
                .andExpect(jsonPath("$.[1].Tickets[0].Price", is(400)));


    }

    @Test
    public void testFlight() throws Exception {
        this.mvc.perform(
                get("/flights/flight")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Tickets[0].Passanger[0].FirstName", is("Some name")))
                .andExpect(jsonPath("$.Tickets[0].Passanger[0].LastName", is("Some other name")))
                .andExpect(jsonPath("$.Tickets[0].Price", is(200)));


    }
}

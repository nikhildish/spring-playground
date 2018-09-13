package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest({FlightController.class,FlightService.class})
@AutoConfigureMockMvc(secure = false)
public class FlightControllerTest {
    @Autowired
    MockMvc mvc;

    Passenger passenger1 = new Passenger("Some name", "Some other name");
    Passenger passenger2 = new Passenger("Name A", "Name B");
    Details details1 = new Details(passenger1, 200);
    Details details2 = new Details(passenger2, 150);

    private Gson gson = new GsonBuilder().create();


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

    @Test
    public void testJsonAsString() throws Exception {
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tickets\": [{\"passenger\": {\"firstName\": \"Some name\",\"lastName\": \"Some other name\"},\"price\": 200},{\"passenger\": {\"firstName\": \"Name B\",\"lastName\": \"Name C\"},\"price\": 150}]}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":350}"));
    }

    @Test
    public void testgetJSONUsingGSON() throws Exception {
        List<Details> detailsList=new ArrayList<>();
        detailsList.add(details1);
        detailsList.add(details2);
        FlightTicket ticket = new FlightTicket(detailsList);

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(ticket));

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":350}"));
    }

    @Test
    public void testGetJSONFromFileFixtures() throws Exception {
        String json = getJSON("/data.json");
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":350}"));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }


}

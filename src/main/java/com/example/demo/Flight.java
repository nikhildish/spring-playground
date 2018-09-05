package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Flight {

    public Flight() {
    }
    @JsonProperty("Departs")
    private Date departs;

    @JsonProperty("Tickets")
    private List<Tickets> tickets;


    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    public Date getDeparts() {
        return departs;
    }

    public void setDeparts(Date dateTime) {
        this.departs = dateTime;
    }

    public List<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<Tickets> tickets) {
        this.tickets = tickets;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Tickets {
        public Tickets() {
        }
        @JsonProperty("Passanger")
        private List<Person> passanger;
        @JsonProperty("Price")
        private int price;

        public List<Person> getPassanger() {
            return passanger;
        }

        public void setPassanger(List<Person> passanger) {
            this.passanger = passanger;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Person {
            @JsonProperty("FirstName")
            private String firstName;

            @JsonProperty("LastName")
            private String lastName;

            public Person() {
            }

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String name) {
                this.firstName = name;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String name) {
                this.lastName = name;
            }
        }
    }
}





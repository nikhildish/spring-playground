package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Flight {

    public Flight() {
    }

    private Date departs;

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

    public static class Tickets {
        public Tickets() {
        }

        private List<Person> passanger;
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


        public static class Person {
            private String firstName;

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





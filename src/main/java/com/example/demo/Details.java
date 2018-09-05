package com.example.demo;

import java.util.List;

public class Details {
    private Passenger passenger;
    private int price;

    public Details() {
    }

    public Details(Passenger passenger, int price) {
        this.passenger = passenger;
        this.price = price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

package me.leon.theater.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private List<Seat> seats = new ArrayList<>();
    private String customerName;
    private Shows show;
    private LocalDateTime purchaseDate;

    public Ticket(List<Seat> seats, String customerName, Shows show, LocalDateTime purchaseDate) {
        this.seats = seats;
        this.customerName = customerName;
        this.show = show;
        this.purchaseDate = purchaseDate;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Shows getShow() {
        return show;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }
}

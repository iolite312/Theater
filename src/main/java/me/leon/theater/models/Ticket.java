package me.leon.theater.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Ticket implements Serializable {
    private List<Seat> seats = new ArrayList<>();
    private String customerName;
    private LocalDateTime purchaseDate;
    private String showingTitle;

    public Ticket(List<Seat> seats, String customerName, LocalDateTime purchaseDate, String showingTitle) {
        this.seats = seats;
        this.customerName = customerName;
        this.purchaseDate = purchaseDate;
        this.showingTitle = showingTitle;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getShowingTitle() {
        return showingTitle;
    }

    public String getPurchaseDate() {
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return purchaseDate.format(formatted);
    }

    public int getCount() {
        return seats.size();
    }
}

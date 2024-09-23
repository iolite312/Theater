package me.leon.theater.models;

import java.time.LocalDateTime;

public class Shows {
    private int id;
    private String showTitle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int totalSeats;
    private int seatsLeft;

    public Shows(int id, String showTitle, LocalDateTime startTime, LocalDateTime endTime, int totalSeats, int seatsLeft) {
        this.id = id;
        this.showTitle = showTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalSeats = totalSeats;
        this.seatsLeft = seatsLeft;
    }

    public int getId() {
        return id;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getSeatsLeft() {
        return seatsLeft;
    }
}

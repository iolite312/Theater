package me.leon.theater.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Shows {
    private int id;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Room room;

    public Shows(int id, String showTitle, LocalDateTime startTime, LocalDateTime endTime, Room room) {
        this.id = id;
        this.title = showTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
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

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

    public String getTitle() {
        return title;
    }

    public String getStartTime() {
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return startTime.format(formatted);
    }

    public String getEndTime() {
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return endTime.format(formatted);
    }

    public Room getRoom() {
        return room;
    }
}

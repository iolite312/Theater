package me.leon.theater.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Shows {
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Room room;

    public Shows(String showTitle, LocalDateTime startTime, LocalDateTime endTime, Room room) {
        this.title = showTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }

    public String getTitle() {
        return title;
    }

    public String getStartTime() {
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return startTime.format(formatted);
    }

    public String getEndTime() {
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return endTime.format(formatted);
    }

    public Room getRoom() {
        return room;
    }
}

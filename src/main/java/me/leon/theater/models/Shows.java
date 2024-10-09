package me.leon.theater.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Shows {
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Room room;
    private List<Ticket> tickets = new ArrayList<>();

    public Shows(String showTitle, LocalDateTime startTime, LocalDateTime endTime, Room room) {
        this.title = showTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }

    public Shows(String title, LocalDateTime startTime, LocalDateTime endTime, Room room, List<Ticket> tickets) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
        this.tickets = tickets;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Room getRoom() {
        return room;
    }

    public String getRoomOccupation() {
        return room.getRoomName() + ": " + (room.getTotalSeats() - ticketSeatCount()) + "/" + room.getTotalSeats() + " left";
    }
    private int ticketSeatCount() {
        int count = 0;
        for (Ticket ticket : tickets) {
            count += ticket.getCount();
        }
        return count;
    }

    @Override
    public String toString() {
        return startTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + " " + title;
    }
}

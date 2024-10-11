package me.leon.theater.models;

import java.io.Serializable;
import java.util.List;

public class Room implements Serializable
{
    private String roomName;
    private int totalSeats;

    public Room(String roomName, int totalSeats) {
        this.roomName = roomName;
        this.totalSeats = totalSeats;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    @Override
    public String toString() {
        return roomName + ": " + totalSeats +" seats";
    }
}

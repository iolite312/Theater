package me.leon.theater.models;

import java.io.Serializable;

public class Room implements Serializable {
    private String roomName;
    private int totalSeats;
    private int rows;
    private int columns;

    public Room(String roomName, int totalSeats, int rows, int columns) {
        this.roomName = roomName;
        this.totalSeats = totalSeats;
        this.rows = rows;
        this.columns = columns;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return roomName + ": " + totalSeats + " seats";
    }
}

package me.leon.theater.models;

public class Room
{
    private String roomName;
    private int totalSeats;
    private int seatsLeft;

    public Room(String roomName, int totalSeats, int seatsLeft) {
        this.roomName = roomName;
        this.totalSeats = totalSeats;
        this.seatsLeft = seatsLeft;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getSeatsLeft() {
        return seatsLeft;
    }

    @Override
    public String toString() {
        return roomName + ": " + seatsLeft + "/" + totalSeats +" left" ;
    }
}

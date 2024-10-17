package me.leon.theater.data;

import me.leon.theater.models.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {
    private List<User> users = new ArrayList<>();
    private List<Shows> shows = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();
    private User loggedInUser;

    public Database() {
        //TODO implement file checks
        seedDatabase();
    }
    private void seedDatabase() {
        createRooms();
        users.add(new User(1, "manager", "1234", Role.MANAGER));
        users.add(new User(2, "sales", "1234", Role.SALES));
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat(4,4));
        seats.add(new Seat(4,5));
        List<Ticket> tickets1 = new ArrayList<>();
        List<Ticket> tickets2 = new ArrayList<>();
        tickets1.add(new Ticket(seats, "Mark de Haan", LocalDateTime.of(2024, 10, 2, 16, 35), "Rebel Moon - Part Two: The ScarGiver"));
        tickets2.add(new Ticket(seats, "Erwin de Vries", LocalDateTime.of(2024, 10, 2, 17, 2), "Rebel Moon - Part Two: The ScarGiver"));
        shows.add(new Shows("Rebel Moon - Part Two: The ScarGiver", LocalDateTime.of(2024, 10, 4, 14, 0), LocalDateTime.of(2024, 10, 4, 16, 30), rooms.getFirst(), tickets1));
        shows.add(new Shows("Rebel Moon - Part Two: The ScarGiver", LocalDateTime.of(2024, 10, 4, 20, 0), LocalDateTime.of(2024, 10, 4, 22, 30), rooms.getFirst(), tickets2));
    }
    private void createRooms() {
        rooms.add(new Room("Room 1", 72, 5, 12));
    }

    public List<User> getUsers() {
        return users;
    }
    public List<Shows> getShows() {
        return shows;
    }
    public List<Room> getRooms() {
        return rooms;
    }
    public User getLoggedInUser() {
        return loggedInUser;
    }
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    public void logOut() {
        loggedInUser = null;
    }
}

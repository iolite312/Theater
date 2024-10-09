package me.leon.theater.data;

import me.leon.theater.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<User> users = new ArrayList<>();
    private List<Shows> shows = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();
    private User loggedInUser;

    public Database() {
        //TODO implement file checks
        seedDatabase();
    }
    private void seedDatabase() {
        users.add(new User(1, "manager", "1234", Role.MANAGER));
        users.add(new User(2, "sales", "1234", Role.SALES));
        shows.add(new Shows("Rebel Moon - Part Two: The ScarGiver", LocalDateTime.of(2024, 10, 4, 14, 0), LocalDateTime.of(2024, 10, 4, 16, 30), new Room("Room 1", 72, 70)));
        shows.add(new Shows("Rebel Moon - Part Two: The ScarGiver", LocalDateTime.of(2024, 10, 5, 20, 0), LocalDateTime.of(2024, 10, 4, 22, 30), ));
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat(4,4));
        seats.add(new Seat(4,5));
        tickets.add(new Ticket(seats, "Mark de Haan", shows.get(0), LocalDateTime.of(2024, 10, 2, 16, 35)));
        tickets.add(new Ticket(seats, "Erwin de Vries", shows.get(1), LocalDateTime.of(2024, 10, 2, 17, 2)));
    }
    private void createRooms() {
        rooms.add(new Room("Room 1", 72, 70));
    }

    public List<User> getUsers() {
        return users;
    }
    public List<Shows> getShows() {
        return shows;
    }
    public List<Ticket> getTickets() {
        return tickets;
    }
    public List
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

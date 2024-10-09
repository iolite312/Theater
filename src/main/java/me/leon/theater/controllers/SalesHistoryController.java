package me.leon.theater.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import me.leon.theater.data.Database;
import me.leon.theater.models.Shows;
import me.leon.theater.models.Ticket;

import java.util.List;

public class SalesHistoryController {
    private Database database;

    private SceneController sceneController;

    @FXML
    private TableView salesTableView;

    private ObservableList<Ticket> tickets;

    public SalesHistoryController(Database database, SceneController sceneController) {
        this.database = database;
        this.sceneController = sceneController;
    }

    @FXML
    public void initialize() {
        tickets = FXCollections.observableArrayList();
        List<Shows> shows = database.getShows();
        for (Shows show : shows) {
            tickets.addAll(show.getTickets());
        }
        salesTableView.setItems(tickets);
    }
}

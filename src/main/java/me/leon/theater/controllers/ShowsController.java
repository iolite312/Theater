package me.leon.theater.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import me.leon.theater.data.Database;
import me.leon.theater.models.Shows;

public class ShowsController {
    private Database database;

    private SceneController sceneController;

    private ObservableList<Shows> shows;

    @FXML
    private TableView showingsTableView;

    public ShowsController(Database database, SceneController sceneController) {
        this.database = database;
        this.sceneController = sceneController;
    }
    @FXML
    public void initialize() {
        shows = FXCollections.observableArrayList(database.getShows());
        showingsTableView.setItems(shows);
    }
}

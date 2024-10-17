package me.leon.theater.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import me.leon.theater.data.Database;
import me.leon.theater.models.Shows;

public class SalesController {
    private Database database;

    private SceneController sceneController;

    private ObservableList<Shows> shows;

    private VBox vbox;

    @FXML
    private TableView<Shows> showingsTableView;
    @FXML
    private Text errorMessage;
    @FXML
    private Text selectedShow;

    public SalesController(Database database, SceneController sceneController, VBox vbox) {
        this.database = database;
        this.sceneController = sceneController;
        this.vbox = vbox;
    }

    @FXML
    public void initialize() {
        shows = FXCollections.observableArrayList(database.getShows());

        showingsTableView.setItems(shows);

        // updates the selected show label when a new show gets selected
        showingsTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && oldValue != newValue) {
                selectedShow.setText(newValue.getFormattedStartTime() + " - " + newValue.getTitle());
            }
        });
    }

    public void sellTickets(ActionEvent actionEvent) {
        errorMessage.setText("");
        Shows show = showingsTableView.getSelectionModel().getSelectedItem();
        if (show == null) {
            errorMessage.setText("No show selected");
            return;
        }
        TicketController ticketController = new TicketController(database, sceneController, show, vbox);

        sceneController.loadSubScenes("ticket-view.fxml", ticketController, vbox);
    }
}
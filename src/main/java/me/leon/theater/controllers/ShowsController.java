package me.leon.theater.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import me.leon.theater.data.Database;
import me.leon.theater.models.Shows;

public class ShowsController {
    private Database database;

    private SceneController sceneController;

    private ObservableList<Shows> shows;

    @FXML
    private TableView showingsTableView;
    @FXML
    private Text errorMessage;

    public ShowsController(Database database, SceneController sceneController) {
        this.database = database;
        this.sceneController = sceneController;
    }
    @FXML
    public void initialize() {
        shows = FXCollections.observableArrayList(database.getShows());
        showingsTableView.setItems(shows);
    }

    public void addShowing(ActionEvent actionEvent) {
        sceneController.StartDialog("Add Showing", "showsDialog-view.fxml", new ShowsDialogController(database));
    }

    public void editShowing(ActionEvent actionEvent) {

    }

    public void deleteShowing(ActionEvent actionEvent) {
        ObservableList<Shows> selectedShow = showingsTableView.getSelectionModel().getSelectedItems();
        if (selectedShow.getFirst().getRoom().getSeatsLeft() == selectedShow.getFirst().getRoom().getTotalSeats()) {
            shows.removeAll(selectedShow);
            return;
        }
        errorMessage.setText("This showing has already sold tickets");
    }
}

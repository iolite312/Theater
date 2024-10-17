package me.leon.theater.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import me.leon.theater.data.Database;
import me.leon.theater.models.Shows;

import java.util.Comparator;

public class ShowsController {
    private Database database;

    private SceneController sceneController;

    private ObservableList<Shows> shows;

    @FXML
    private TableView<Shows> showingsTableView;
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
        ShowsDialogController showsDialogController = new ShowsDialogController(database);

        sceneController.StartDialog("Add Showing", "showsDialog-view.fxml", showsDialogController);

        if (showsDialogController.getShow() != null) {
            shows.add(showsDialogController.getShow());
            database.getShows().add(showsDialogController.getShow());
            sortShows();
            showingsTableView.refresh();
        }
    }

    public void editShowing(ActionEvent actionEvent) {
        if (showingsTableView.getSelectionModel().getSelectedItems().isEmpty()) {
            errorMessage.setText("No shows selected");
            return;
        }
        Shows show = showingsTableView.getSelectionModel().getSelectedItems().getFirst();
        ShowsDialogController showsDialogController = new ShowsDialogController(show, database);

        sceneController.StartDialog("Edit Showing", "showsDialog-view.fxml", showsDialogController);

        if (showsDialogController.getShow() != null) {
            shows.remove(show);
            shows.add(showsDialogController.getShow());
            sortShows();
            showingsTableView.refresh();
        }
    }

    public void deleteShowing(ActionEvent actionEvent) {
        errorMessage.setText("");
        Shows selectedShow = showingsTableView.getSelectionModel().getSelectedItem();

        if (selectedShow.getTickets().isEmpty()) {
            shows.removeAll(selectedShow);
            database.getShows().remove(selectedShow);
            return;
        }

        errorMessage.setText("This showing has already sold tickets");
    }

    private void sortShows() {
        shows.sort(Comparator.comparing(Shows::getStartTime));
    }
}
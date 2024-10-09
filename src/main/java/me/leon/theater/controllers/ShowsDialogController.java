package me.leon.theater.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import me.leon.theater.data.Database;
import me.leon.theater.models.Room;
import me.leon.theater.models.Shows;

public class ShowsDialogController {
    Shows show;

    Database database;

    public Shows getShow() {
        return show;
    }

    @FXML
    TextField title;
    @FXML
    ComboBox<Room> roomSelector;
    @FXML
    DatePicker startDate;
    @FXML
    TextField startTime;
    @FXML
    DatePicker endDate;
    @FXML
    TextField endTime;
    @FXML
    Text errorLabel;

    public ShowsDialogController(Database database) {
        this.database = database;
    }
    public void initialize() {
        database.ge
    }

    public void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void confirmShowing(ActionEvent event) {
        if (startDate.getValue() == null || endDate.getValue() == null) { return;}
        if (startDate.getValue().isAfter(endDate.getValue())) {
            errorLabel.setText("Start date is after end date");
        }
//        show = new Shows(title)
    }
}

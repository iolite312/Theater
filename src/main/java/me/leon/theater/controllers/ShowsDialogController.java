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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ShowsDialogController {
    Shows show;

    public Shows getShow() {
        return show;
    }

    Database database;

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
        roomSelector.getItems().addAll(database.getRooms());
    }

    public void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void confirmShowing(ActionEvent event) {
        errorLabel.setText("");
        if (Objects.equals(title.getText(), "")) {
            errorLabel.setText("No title set"); return;
        }
        if (roomSelector.getSelectionModel().isEmpty()) {
            errorLabel.setText("No room set"); return;
        }
        if (startDate.getValue() == null || endDate.getValue() == null) {
            errorLabel.setText("No start or end date set"); return;
        }
        if (Objects.equals(startTime.getText(), "") || Objects.equals(endTime.getText(), "")) {
            errorLabel.setText("No start or end time set"); return;
        }
        if (startDate.getValue().isAfter(endDate.getValue())) {
            errorLabel.setText("Start date is after end date"); return;
        }
        show = new Shows(title.getText(), formatTime(startDate.getValue(), startTime.getText()), formatTime(endDate.getValue(), endTime.getText()), roomSelector.getValue());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
    private LocalDateTime formatTime(LocalDate date, String timeText) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse(timeText, formatter);
        return LocalDateTime.of(date, time);
    }
}

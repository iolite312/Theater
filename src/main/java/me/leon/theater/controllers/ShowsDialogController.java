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
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class ShowsDialogController {
    private Shows show;
    private Database database;
    @FXML
    private Text header;
    @FXML
    private TextField title;
    @FXML
    private ComboBox<Room> roomSelector;
    @FXML
    private DatePicker startDate;
    @FXML
    private TextField startTime;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextField endTime;
    @FXML
    private Text errorLabel;
    @FXML
    private Button confirmBtn;
    public ShowsDialogController(Database database) {
        this.database = database;
    }

    public ShowsDialogController(Shows show, Database database) {
        this.show = show;
        this.database = database;
    }

    public Shows getShow() {
        return show;
    }

    public void initialize() {
        roomSelector.getItems().addAll(database.getRooms());
        //checks is there is a valid show model and if true load that data instead of an empty form
        if (show != null) {
            roomSelector.setValue(show.getRoom());
            title.setText(show.getTitle());
            startTime.setText(show.getStartTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            endTime.setText(show.getEndTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            startDate.setValue(show.getStartTime().toLocalDate());
            endDate.setValue(show.getEndTime().toLocalDate());
            header.setText("Edit Showing");
            confirmBtn.setText("Edit Showing");
            if (!show.getTickets().isEmpty()) {
                roomSelector.setDisable(true);
            }
        }
    }

    public void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void confirmShowing(ActionEvent event) {
        if (validateFormInputs()) return;
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //validates the whole form
    private boolean validateFormInputs() {
        errorLabel.setText("");
        if (Objects.equals(title.getText(), "")) {
            errorLabel.setText("No title set");
            return true;
        }
        if (roomSelector.getSelectionModel().isEmpty()) {
            errorLabel.setText("No room set");
            return true;
        }
        if (startDate.getValue() == null || endDate.getValue() == null) {
            errorLabel.setText("No start or end date set");
            return true;
        }
        if (Objects.equals(startTime.getText(), "") || Objects.equals(endTime.getText(), "")) {
            errorLabel.setText("No start or end time set");
            return true;
        }
        if (startDate.getValue().isAfter(endDate.getValue())) {
            errorLabel.setText("Start date is after end date");
            return true;
        }
        if (!validateTime()) {
            errorLabel.setText("Invalid time please conform to this 00:00 format");
            return true;
        }
        if (show == null) {
            show = new Shows(title.getText(), formatTime(startDate.getValue(), startTime.getText()), formatTime(endDate.getValue(), endTime.getText()), roomSelector.getValue());
        } else {
            show.updateShow(title.getText(), formatTime(startDate.getValue(), startTime.getText()), formatTime(endDate.getValue(), endTime.getText()), roomSelector.getValue());
        }
        return false;
    }

    private LocalDateTime formatTime(LocalDate date, String timeText) {
        LocalTime time = LocalTime.parse(timeText, DateTimeFormatter.ofPattern("HH:mm"));
        return LocalDateTime.of(date, time);
    }

    private boolean validateTime() {
        try {
            LocalTime.parse(startTime.getText(), DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime.parse(endTime.getText(), DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}

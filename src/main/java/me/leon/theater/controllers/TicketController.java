package me.leon.theater.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import me.leon.theater.data.Database;
import me.leon.theater.models.Room;
import me.leon.theater.models.Seat;
import me.leon.theater.models.Shows;
import me.leon.theater.models.Ticket;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketController {
    private Database database;
    private SceneController sceneController;
    private Shows show;

    private List<Seat> occupiedSeats = new ArrayList<>();
    private ObservableList<Seat> freeSeats = FXCollections.observableArrayList();

    @FXML
    GridPane gridPane;
    @FXML
    AnchorPane anchorPane;
    @FXML
    ListView<Seat> seatList;
    @FXML
    Button sellTicketsbtn;
    @FXML
    TextField customerNameInput;
    @FXML
    VBox mainLayout;

    public TicketController(Database database, SceneController sceneController, Shows show, VBox mainLayout) {
        this.database = database;
        this.sceneController = sceneController;
        this.show = show;
        this.mainLayout = mainLayout;
    }

    public void initialize() {
        seatList.setItems(freeSeats);

        for (Ticket ticket : show.getTickets()) {
            occupiedSeats.addAll(ticket.getSeats());
        }
        Room room = show.getRoom();
        int roomRows = room.getRows();
        int roomColumns = room.getColumns();
        initSelector(roomRows, roomColumns + 1);
        updateText();
    }
    private void initSelector(int roomRows, int roomColumns) {
        initGridPane(roomRows, roomColumns);
        for (int i = 0; i < roomRows; i++) {
            for (int j = 0; j < roomColumns; j++) {
                if (j == 0) {
                    Label label = new Label();
                    label.setText("Row " + (i +1));
                    gridPane.add(label, 0, i);
                    continue;
                }
                gridPane.add(createSeatButton(i, j), j, i);
            }
        }
    }
    private void initGridPane(int row, int col) {
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int i = 0; i < row; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / row);
            gridPane.getRowConstraints().add(rowConstraints);
        }
        for (int i = 0; i < col; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / col);
            gridPane.getColumnConstraints().add(columnConstraints);
        }
    }

    private boolean isOccupiedSeat(Seat seat) {
        for (Seat occupiedSeat : occupiedSeats) {
            if (occupiedSeat.getRow() == seat.getRow() && occupiedSeat.getColumn() == seat.getColumn()) {
                return true;
            }
        }
        return false;
    }

    private Button createSeatButton(int row, int col) {
        Seat seat = new Seat(row + 1, col);

        Button seatButton = new Button();
        seatButton.setText(String.valueOf(col));
        seatButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        GridPane.setHgrow(seatButton, Priority.ALWAYS);
        GridPane.setVgrow(seatButton, Priority.ALWAYS);
        seatButton.setUserData(seat);
        seatButton.setTextFill(Paint.valueOf("#ffffff"));
        seatButton.setFont(new Font(20));

        if(isOccupiedSeat(seat)) {
            seatButton.setBackground(Background.fill(Paint.valueOf("#ff0000")));
        } else {
            seatButton.setBackground(Background.fill(Paint.valueOf("#4a4a4a")));

            seatButton.setOnAction(actionEvent -> {
                if (freeSeats.contains(seat)) {
                    seatButton.setBackground(Background.fill(Paint.valueOf("#4a4a4a")));
                    freeSeats.remove(seat);
                } else {
                    seatButton.setBackground(Background.fill(Paint.valueOf("#00b503")));
                    freeSeats.add(seat);
                }
                updateText();
            });
        }
        return seatButton;
    }

    private void updateText() {
        int ticketSize = freeSeats.size();
        String customerName = customerNameInput.getText();
        sellTicketsbtn.setDisable(ticketSize == 0 || customerName.isEmpty());
        sellTicketsbtn.setText("Sell "+ ticketSize + " tickets");
    }
    public void inputUpdated(KeyEvent key) {
        updateText();
    }
    public void cancelSale(ActionEvent actionEvent) {
        sceneController.loadSubScenes("sales-view.fxml", new SalesController(database, sceneController, mainLayout), mainLayout);
    }

    public void sellTickets(ActionEvent actionEvent) {
        Ticket ticket = new Ticket(freeSeats.stream().toList(), customerNameInput.getText(), LocalDateTime.now(), show.getTitle());
        show.getTickets().add(ticket);
        sceneController.loadSubScenes("sales-view.fxml", new SalesController(database, sceneController, mainLayout), mainLayout);
    }
}

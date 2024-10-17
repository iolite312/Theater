package me.leon.theater.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import me.leon.theater.data.Database;

public class HomeController {
    private Database database;

    private SceneController sceneController;
    public HomeController(Database database, SceneController sceneController) {
        this.database = database;
        this.sceneController = sceneController;
    }
    @FXML
    private Text usernameWelcome;

    @FXML
    private Text roleWelcome;

    @FXML
    private Text currentDateTime;

    @FXML
    public void initialize() {
        usernameWelcome.setText("Welcome " +database.getLoggedInUser().getUserName());
        roleWelcome.setText("You are logged in as " + database.getLoggedInUser().getRole().toString().toLowerCase());

        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime nowTemp = LocalDateTime.now();
        currentDateTime.setText("The current date and time is " + formatted.format(nowTemp));
        Timeline clock = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime now = LocalDateTime.now();
                    currentDateTime.setText("The current date and time is " + formatted.format(now));
                })
        );
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }
}

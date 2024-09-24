package me.leon.theater.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import me.leon.theater.data.Database;

import java.io.IOException;

public class DashboardController {

    private Database database;
    private SceneController sceneController;
    @FXML
    VBox layout;

    public DashboardController(Database database, SceneController sceneController) {
        this.database = database;
        this.sceneController = sceneController;
    }

    public void loadSubScene(ActionEvent event) {
        sceneController.loadSubScenes("home-view.fxml", new HomeController(database, sceneController), layout);
    }
    public void loadSubScene2(ActionEvent event) {
        sceneController.loadSubScenes("sales-view.fxml", new HomeController(database, sceneController), layout);
    }
}

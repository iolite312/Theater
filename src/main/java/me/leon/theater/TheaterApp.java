package me.leon.theater;

import javafx.application.Application;
import javafx.stage.Stage;
import me.leon.theater.controllers.SceneController;
import me.leon.theater.data.Database;

import java.io.IOException;

public class TheaterApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Database database = new Database();
        SceneController sceneController = new SceneController(database, stage);
        sceneController.setRootScene("login");
    }

    public static void main(String[] args) {
        launch();
    }
}
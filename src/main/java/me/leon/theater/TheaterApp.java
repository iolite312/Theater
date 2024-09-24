package me.leon.theater;

import javafx.application.Application;
import javafx.stage.Stage;
import me.leon.theater.controllers.SceneController;
import me.leon.theater.data.Database;

import java.io.IOException;

public class TheaterApp extends Application {
    Database database = new Database();

    @Override
    public void start(Stage stage) throws IOException {
        SceneController sceneController = new SceneController(database, stage);
        sceneController.setRootScene("login");
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() {
        System.out.println(database.getLoggedInUser().getUserName());
        database.logOut();
        System.out.println("Stage is closing");
        System.out.println(database.getLoggedInUser());
    }
}
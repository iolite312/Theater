package me.leon.theater;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.leon.theater.models.Database;

import java.io.IOException;

public class TheaterApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Database database = new Database();
        FXMLLoader fxmlLoader = new FXMLLoader(TheaterApp.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
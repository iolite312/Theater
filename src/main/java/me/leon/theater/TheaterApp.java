package me.leon.theater;

import javafx.application.Application;
import javafx.stage.Stage;
import me.leon.theater.controllers.SceneController;
import me.leon.theater.data.Database;

import java.io.*;

public class TheaterApp extends Application {
    private Database database;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("theaterDatabase.dat"))) {
            database = (Database) ois.readObject();
        } catch (Exception e) {
            database = new Database();
            System.out.println("Continuing with default database");
        }
        SceneController sceneController = new SceneController(database, stage);
        sceneController.setRootScene("login");
    }

    @Override
    public void stop() {
        if (database.getLoggedInUser() == null) {
            return;
        }
        database.logOut();
        try (FileOutputStream fos = new FileOutputStream("theaterDatabase.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(database);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
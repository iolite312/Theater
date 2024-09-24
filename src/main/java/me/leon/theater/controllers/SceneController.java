package me.leon.theater.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.leon.theater.TheaterApp;
import me.leon.theater.data.Database;

import java.io.IOException;
import java.util.HashMap;


public class SceneController {
    private Parent root;
    private Stage stage;
    private Database database;
    private HashMap<String, String> scenes = new HashMap<>();

    public SceneController(Database database, Stage stage) {
        this.stage = stage;
        this.database = database;
        addScenePaths();
    }

    public void addScenePaths() {
        scenes.put("login", "login-view.fxml");
        scenes.put("home", "home-view.fxml");
    }

    public void setRootScene(String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(TheaterApp.class.getResource(scenes.get(name)));
        if ("login".equals(name)) {
            loader.setController(new LoginController(database, this));
        } else if ("home".equals(name)) {
            loader.setController(new HomeViewController(database, this));
        }

        root = loader.load();
        if (root == null) {
            throw new IllegalArgumentException("Scene not found");
        }
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void setRootScene(String name, ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(TheaterApp.class.getResource(scenes.get(name)));

        if ("login".equals(name)) {
            loader.setController(new LoginController(database, this));
        } else if ("home".equals(name)) {
            loader.setController(new HomeViewController(database, this));
        }

        root = loader.load();
        if (root == null) {
            throw new IllegalArgumentException("Scene not found");
        }
        Scene scene = new Scene(root);

        stage.setScene(scene);
    }
}

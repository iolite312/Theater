package me.leon.theater.controllers;

import javafx.event.ActionEvent;
import me.leon.theater.data.Database;
import me.leon.theater.models.Role;
import me.leon.theater.models.User;

import java.io.IOException;

public class HomeViewController {
    private Database database;

    private SceneController sceneController;
    public HomeViewController(Database database, SceneController sceneController) {
        this.database = database;
        this.sceneController = sceneController;
    }

    public void onLogout(ActionEvent event) throws IOException {
        sceneController.setRootScene("login", event);
    }
}

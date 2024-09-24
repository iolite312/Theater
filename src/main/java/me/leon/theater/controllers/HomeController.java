package me.leon.theater.controllers;

import me.leon.theater.data.Database;

public class HomeController {
    private Database database;

    private SceneController sceneController;
    public HomeController(Database database, SceneController sceneController) {
        this.database = database;
        this.sceneController = sceneController;
    }
}

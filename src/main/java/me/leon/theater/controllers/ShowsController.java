package me.leon.theater.controllers;

import me.leon.theater.data.Database;

public class ShowsController {
    private Database database;

    private SceneController sceneController;
    public ShowsController(Database database, SceneController sceneController) {
        this.database = database;
        this.sceneController = sceneController;
    }
}

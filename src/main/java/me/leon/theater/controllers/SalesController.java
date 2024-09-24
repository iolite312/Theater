package me.leon.theater.controllers;

import me.leon.theater.data.Database;

public class SalesController {
    private Database database;

    private SceneController sceneController;
    public SalesController(Database database, SceneController sceneController) {
        this.database = database;
        this.sceneController = sceneController;
    }
}

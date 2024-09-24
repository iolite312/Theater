package me.leon.theater.controllers;

import me.leon.theater.data.Database;

public class SalesHistoryController {
    private Database database;

    private SceneController sceneController;
    public SalesHistoryController(Database database, SceneController sceneController) {
        this.database = database;
        this.sceneController = sceneController;
    }
}

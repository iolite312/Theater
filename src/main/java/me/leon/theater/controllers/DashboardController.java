package me.leon.theater.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import me.leon.theater.data.Database;
import me.leon.theater.models.Role;

public class DashboardController {

    private Database database;
    private SceneController sceneController;
    @FXML
    private VBox layout;
    @FXML
    private Button manageShowingBtn;
    @FXML
    private Button viewSalesHistoryBtn;

    public DashboardController(Database database, SceneController sceneController) {
        this.database = database;
        this.sceneController = sceneController;
    }

    public void loadSales(ActionEvent event) {
        sceneController.loadSubScenes("sales-view.fxml", new SalesController(database, sceneController, layout), layout);
    }

    public void loadShows(ActionEvent event) {
        sceneController.loadSubScenes("shows-view.fxml", new ShowsController(database, sceneController), layout);
    }

    public void loadSalesHistory(ActionEvent event) {
        sceneController.loadSubScenes("salesHistory-view.fxml", new SalesHistoryController(database, sceneController), layout);
    }
    public void goToHome() {
        sceneController.loadSubScenes("home-view.fxml", new HomeController(database, sceneController), layout);
    }
    public void logOut(ActionEvent event) {
        database.logOut();
        sceneController.setRootScene("login", event);
    }
    @FXML
    public void initialize() {
        sceneController.loadSubScenes("home-view.fxml", new HomeController(database, sceneController), layout);
        manageShowingBtn.setDisable(database.getLoggedInUser().getRole() == Role.SALES);
        viewSalesHistoryBtn.setDisable(database.getLoggedInUser().getRole() == Role.SALES);
    }
}

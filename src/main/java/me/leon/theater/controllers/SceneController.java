package me.leon.theater.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
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
        scenes.put("dashboard", "dashboard-view.fxml");
    }

    public void setRootScene(String name) {
        //sets the main stage when the application is starting
        SceneLoader(name);
        stage.show();
    }

    public void setRootScene(String name, ActionEvent actionEvent) {
        //sets the main stage when the application is already loaded in
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        SceneLoader(name);
    }

    private void SceneLoader(String name) {
        try {
            //gets the correct fxml and assigns the correct controller to it for the main stage
            FXMLLoader loader = new FXMLLoader(TheaterApp.class.getResource(scenes.get(name)));
            if ("login".equals(name)) {
                loader.setController(new LoginController(database, this));
            } else if ("dashboard".equals(name)) {
                loader.setController(new DashboardController(database, this));
            }

            root = loader.load();
            if (root == null) {
                throw new IllegalArgumentException("Scene not found");
            }
            Scene scene = new Scene(root);

            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Swaps all the subScenes when needed. this to keep the header static
    public void loadSubScenes(String name, Object controller, VBox layout) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TheaterApp.class.getResource(name));
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            if (layout.getChildren().size() > 1)
                layout.getChildren().remove(1);
            layout.getChildren().add(scene.getRoot());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //creates dialog popups, for example for editing a show
    public void StartDialog(String name, String view, Object controller) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TheaterApp.class.getResource(view));
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());

            Stage dialog = new Stage();
            dialog.setTitle(name);
            dialog.setScene(scene);
            dialog.showAndWait();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

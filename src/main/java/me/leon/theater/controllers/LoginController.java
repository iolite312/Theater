package me.leon.theater.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import me.leon.theater.data.Database;
import me.leon.theater.models.User;

import java.io.IOException;
import java.util.List;

public class LoginController {
    private Database database;

    @FXML
    private TextField userNameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Text userNameErrorTxt;

    @FXML
    private Text passwordErrorTxt;

    private SceneController sceneController;

    public LoginController(Database database, SceneController sceneController) {
        this.database = database;
        this.sceneController = sceneController;
    }

    public void onLoginButtonClick(ActionEvent event) throws IOException {
        String username = userNameInput.getText();
        String password = passwordInput.getText();
        List<User> users = database.getUsers();

        boolean found = false;
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                found = true;
                if (user.getPassword().equals(password)) {
                    database.setLoggedInUser(user);
                    sceneController.setRootScene("dashboard", event);
                    break;
                }
                passwordErrorTxt.setText("Wrong password!");
            }
        }
        if (!found) {
            userNameErrorTxt.setText("User not found!");
        }
    }
}
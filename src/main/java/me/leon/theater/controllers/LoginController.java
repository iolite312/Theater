package me.leon.theater.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import me.leon.theater.data.Database;
import me.leon.theater.models.User;

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

    public LoginController() {
        this.database = new Database();
    }

    public void OnLoginButtonClick(ActionEvent actionEvent) {
        String username = userNameInput.getText();
        String password = passwordInput.getText();
        List<User> users = database.getUsers();

        boolean found = false;
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                found = true;
                if (user.getPassword().equals(password)) {
                    userNameErrorTxt.setText("Successfully logged in!");
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
package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.services.*;

public class LoginController {

    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;

    @FXML
    public void initialize() {
        role.getItems().addAll("Student", "Admin");
    }

    @FXML
    public void handleLoginAction() {
        try {
            Stage stage = (Stage) loginMessage.getScene().getWindow();
            Parent loginRoot = FXMLLoader.load(getClass().getResource("./"))
        } catch (UsernameAlreadyExistsException e) {
            LoginMessage.setText(e.getMessage());
        }
    }
}

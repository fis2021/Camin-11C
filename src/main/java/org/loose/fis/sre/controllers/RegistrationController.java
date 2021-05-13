package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.window;
import org.loose.fis.sre.services.*;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;
    @FXML
    private Button loginButton;

    @FXML
    public void initialize() {
        role.getItems().addAll("Student", "Admin");
    }

    @FXML
    public void handleRegisterAction() throws UsernameAlreadyExistsException{
        UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
        registrationMessage.setText("Account created successfully!");
    }

    @FXML
    public void handleLoginAction(){
        try {
            window.createWindow("login.fxml",loginButton);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
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
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.IncorrectLoginException;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class LoginController {
    private final ObjectRepository<User> userRepository = UserService.getUserRepository();

    @FXML
    private Text loginMessage;
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
    public void handleLoginAction(){
        try {
            Stage stage = new Stage();
            if(!UserService.checkUserDoesNotAlreadyExist(usernameField.getText(),passwordField.getText())) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("studentPage.fxml"));
                Parent root = loader.load();
                stage.setScene(new Scene(root));
                stage.show();
                StudentPageController.setClientUsername(usernameField.getText());
            }
        } catch (IncorrectLoginException | IOException e) {
            loginMessage.setText(e.getMessage());
        }
    }
}

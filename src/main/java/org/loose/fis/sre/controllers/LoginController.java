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
import org.loose.fis.sre.exceptions.IncorrectLoginException;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

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
    public void handleLoginAction() throws IncorrectLoginException {
        try {

            if (UserService.checkForAccount(usernameField.getText(),passwordField.getText()) == true) {
                System.out.println("Account Exists");
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("studentPage.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                throw new IncorrectLoginException("Account does not exist");
            }
        } catch (IncorrectLoginException e) {
            System.out.println(e);
        } catch (IOException ee) {
            System.out.println("ooops");
        }
    }
}
package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.IncorrectLoginException;
import org.loose.fis.sre.exceptions.UsernameDoesNotExistException;
import org.loose.fis.sre.model.window;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class LoginController {


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
    private Button backToRegisterButton;


    @FXML
    public void initialize() {
        role.getItems().addAll("Student", "Admin");
    }
    
    @FXML
    public void handleLoginAction(){
        try {

            if (UserService.isLoginCorrect(usernameField.getText(),
                    passwordField.getText(),
                    role.getValue().toString()) == true) {
                if(role.getValue() == "Student") {
                    window.createWindow("studentPage.fxml",loginButton);
                } else {
                    window.createWindow("adminPage.fxml",loginButton);
                }
            } else {
                if(UserService.checkWrongRole(usernameField.getText(),(String) role.getValue()) == false){
                    loginMessage.setText("You selected the wrong role for your user!");
                } else{
                    loginMessage.setText("Wrong Password!");
                }
                throw new IncorrectLoginException("Account does not exist");
            }
        } catch (IncorrectLoginException e) {
            System.out.println(e.getMessage());
        } catch (IOException ee) {
            System.out.println(ee.getMessage());
        } catch (UsernameDoesNotExistException e) {
            e.printStackTrace();
        }
    }
    public void handleBackToRegisterAction() {
        window.goBackWindow("register.fxml",backToRegisterButton);
    }
}
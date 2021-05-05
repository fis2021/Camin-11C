package org.loose.fis.sre.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.loose.fis.sre.services.LaundryService;

import java.io.IOException;

public class LaundryAppointmentController {

    @FXML
    private TextField roomField;
    @FXML
    private TextField dayField;
    @FXML
    private TextField hourField;
    @FXML
    private Button backToStudentHomePageButton;
    public void handleBackToStudentHomePageAction() {
        try{
            Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("studentPage.fxml"));
            Stage stage = (Stage) (backToStudentHomePageButton.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    public void handleLaundryAppointmentAction(){
        LaundryService.addLaundry(Integer.parseInt(roomField.getText()),
                dayField.getText(),
                Integer.parseInt(hourField.getText()));
    }
}
package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.loose.fis.sre.model.window;

import java.io.IOException;

public class StudentsEvidenceController {

    @FXML
    private Button backToAdminHomePageButton;

    public void handleBackToAdminHomePageAction() {
        try{
            Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("adminPage.fxml"));
            Stage stage = (Stage) (backToAdminHomePageButton.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }


}

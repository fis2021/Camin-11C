package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentPageController {
    @FXML
    private Button roomProfileButton;
    @FXML
    private Button laundryScheduleButton;

    public void handleRoomProfileAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("roomProfile.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) roomProfileButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleLaundryScheduleAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("laundrySchedule.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) laundryScheduleButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

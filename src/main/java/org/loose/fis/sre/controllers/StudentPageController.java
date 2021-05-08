package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.model.Announcement;
import org.loose.fis.sre.model.window;
import org.loose.fis.sre.services.AnnouncementService;

import java.io.IOException;

public class StudentPageController {
    @FXML
    private Button roomProfileButton;
    @FXML
    private Button laundryScheduleButton;
    @FXML
    private Button checkPaymentsButton;
    @FXML
    private Button studentAnnouncementsButton;
    @FXML
    private Button laundryAppointmentButton;
    @FXML
    private Button LogoutButton;

    public void handleRoomProfileAction() throws IOException {
        window.createWindow("roomProfile.fxml", roomProfileButton);
    }

    public void handleLaundryScheduleAction() throws IOException {
        window.createWindow("laundrySchedule.fxml", laundryScheduleButton);
    }

    public void handleCheckPaymentsAction() throws IOException {
        window.createWindow("paymentsSituation.fxml", checkPaymentsButton);
    }

    public void handleStudentAnnouncementsAction() throws IOException {
        window.createWindow("studentAnnouncements.fxml", studentAnnouncementsButton);
    }
    public void handleLaundryAppointmentAction() throws IOException {
        window.createWindow("laundryAppointment.fxml",laundryAppointmentButton);
    }


    public void handleLogout() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Stage stage = (Stage) (LogoutButton.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }


}

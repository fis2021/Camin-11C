package org.loose.fis.sre.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.loose.fis.sre.model.window;
import org.loose.fis.sre.services.AnnouncementService;

import java.io.IOException;

public class AdminHomePageController {
    @FXML
    private Button addRoomButton;
    @FXML
    private Button announcementButton;
    @FXML
    private TextField announcementField;
    @FXML
    private Button postAnnouncementButton;
    @FXML
    private Button studentsEvidenceButton;
    @FXML
    private Button studentPaymentEvidenceButton;
    @FXML
    private Button LogoutButton;

    public void handleAnnouncementAction() throws IOException{
        announcementField.setVisible(!announcementField.isVisible());
        postAnnouncementButton.setVisible(!postAnnouncementButton.isVisible());
    }

    public void handlePostAnnouncementAction() throws IOException{
        AnnouncementService.addAnnouncement(announcementField.getText());
    }

    public void handleStudentsEvidenceAction() throws IOException {
        window.createWindow("studentsEvidence.fxml",studentsEvidenceButton);
    }
    public void handlePaymentEvidenceAction() throws IOException {
        window.createWindow("studentPaymentEvidence.fxml",studentPaymentEvidenceButton);
    }

    public void handleAddRoomButton() throws IOException {
        window.createWindow("addRoom.fxml",addRoomButton);
    }

    public void handleLogout() {
        window.goBackWindow("login.fxml",LogoutButton);
    }
}

package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.loose.fis.sre.model.window;
import org.loose.fis.sre.services.AnnouncementService;

import java.util.ArrayList;

public class StudentAnnouncementsController {
    @FXML
    private ListView announcementsList;
    @FXML
    private Button showAnnouncementsButton;
    @FXML
    private Button backToStudentHomePageButton;

    public void handleShowAnnouncementsAction(){
        announcementsList.setVisible(true);
        ArrayList<String> announcements = new ArrayList<>();
        announcements = AnnouncementService.getAnnouncements();
        announcementsList.getItems().addAll(announcements);
    }

    public void handleBackToStudentHomePageAction() {
        window.goBackWindow("studentPage.fxml",backToStudentHomePageButton);
    }
}

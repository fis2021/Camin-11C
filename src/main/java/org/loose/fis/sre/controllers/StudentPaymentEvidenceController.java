package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.loose.fis.sre.model.window;

import java.io.IOException;

public class StudentPaymentEvidenceController {

    @FXML
    private Button backToAdminHomePageButton;

    public void handleBackToAdminHomePageAction() {
        window.goBackWindow("adminPage.fxml",backToAdminHomePageButton);
    }
}

package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.loose.fis.sre.model.window;
import org.loose.fis.sre.services.LaundryService;

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
        window.goBackWindow("studentPage.fxml",backToStudentHomePageButton);
    }

    @FXML
    public void handleLaundryAppointmentAction(){
        LaundryService.addLaundry(Integer.parseInt(roomField.getText()),
                dayField.getText(),
                Integer.parseInt(hourField.getText()));
    }
}

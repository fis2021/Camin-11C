package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.loose.fis.sre.model.window;

import java.io.IOException;

public class StudentPageController {
    @FXML
    private Button roomProfileButton;
    @FXML
    private Button laundryScheduleButton;
    @FXML
    private Button checkPaymentsButton;
    @FXML
    private Button laundryAppointmentButton; //facem form?

    public void handleRoomProfileAction() throws IOException {
        window.createWindow("roomProfile.fxml",roomProfileButton);
    }

    public void handleLaundryScheduleAction() throws IOException {
        window.createWindow("roomProfile.fxml",laundryScheduleButton);
    }

    public void handleCheckPaymentsAction()  throws IOException
    {
        window.createWindow("paymentsSituation.fxml",checkPaymentsButton);
    }





}

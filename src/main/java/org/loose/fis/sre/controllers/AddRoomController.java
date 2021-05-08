package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.loose.fis.sre.model.Announcement;
import org.loose.fis.sre.model.Room;
import org.loose.fis.sre.model.window;
import org.loose.fis.sre.services.AnnouncementService;
import org.loose.fis.sre.services.RoomService;
import org.loose.fis.sre.services.UserService;

import java.util.ArrayList;


public class AddRoomController {
    @FXML
    private TextField roomField;
    @FXML
    private TextField floorField;
    @FXML
    private TextField student1;
    @FXML
    private TextField student2;
    @FXML
    private Button addRoomButton;
    @FXML
    private Button goBackButton;

    @FXML
    public void handleAddRoomAction() {
        RoomService.addRoom(Integer.parseInt(roomField.getText()),
                Integer.parseInt(floorField.getText()),
                student1.getText(), student2.getText());


    }
    @FXML
    public void handleGoBackAction(){
        window.goBackWindow("adminPage.fxml",goBackButton);
    }
}

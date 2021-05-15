package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.loose.fis.sre.exceptions.RoomAlreadyExistsException;
import org.loose.fis.sre.model.window;
import org.loose.fis.sre.services.RoomService;


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
    public void handleAddRoomAction() throws RoomAlreadyExistsException {
        RoomService.addRoom(Integer.parseInt(roomField.getText()),
                Integer.parseInt(floorField.getText()),
                student1.getText(), student2.getText());


    }
    @FXML
    public void handleGoBackAction(){
        window.goBackWindow("adminPage.fxml",goBackButton);
    }
}

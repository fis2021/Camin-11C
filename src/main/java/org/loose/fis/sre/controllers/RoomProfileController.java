package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import org.loose.fis.sre.model.Room;
import org.loose.fis.sre.model.window;
import org.loose.fis.sre.services.RoomService;
import org.loose.fis.sre.services.UserService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomProfileController implements Initializable {
    @FXML
    private DialogPane nrRoom;
    @FXML
    private DialogPane floor;
    @FXML
    private DialogPane roommate;
    @FXML
    private Button backToStudentHomePageButton;

    public void handleBackToStudentHomePageAction() {
        window.goBackWindow("studentPage.fxml", backToStudentHomePageButton);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Room> rooms = RoomService.getRooms();
        for (Room room : rooms) {
            if (room.getStudent1().equals(UserService.getLoggedUser()) ||
                    room.getStudent2().equals(UserService.getLoggedUser())) {
                nrRoom.setContentText(String.valueOf(room.getNrRoom()));
                floor.setContentText(String.valueOf(room.getFloor()));
                if (room.getStudent1().equals(UserService.getLoggedUser())) {
                    roommate.setContentText(room.getStudent2());
                } else {
                    roommate.setContentText(room.getStudent1());
                }

            }
        }
    }
}


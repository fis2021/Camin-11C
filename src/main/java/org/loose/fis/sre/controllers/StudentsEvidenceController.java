package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.loose.fis.sre.model.Evidence;
import org.loose.fis.sre.model.Room;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.model.window;
import org.loose.fis.sre.services.RoomService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentsEvidenceController implements Initializable {
    @FXML
    private TableView<Evidence> evidenceTable;
    @FXML
    private TableColumn<Evidence, String> nameCol;
    @FXML
    private TableColumn<Evidence, Integer> roomCol;
    @FXML
    private Button backToAdminHomePageButton;

    public void handleBackToAdminHomePageAction() {
        window.goBackWindow("adminPage.fxml",backToAdminHomePageButton);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<User> users = UserService.getUsers();
        ArrayList<Room> rooms = RoomService.getRooms();
        ArrayList<Evidence> evidenceList = new ArrayList<>();

        for (User user : UserService.getUserRepository().find()) {
            for (Room room : RoomService.getRoomRepository().find()) {
                if (user.getUsername().equals(room.getStudent1()) ||
                        user.getUsername().equals(room.getStudent2())) {
                    evidenceList.add(new Evidence(user.getUsername(), room.getNrRoom()));

                }
            }
        }

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
        ObservableList<Evidence> observableList = FXCollections.observableArrayList(evidenceList);
        evidenceTable.setItems(observableList);
        System.out.println("Showing the students");
    }
}

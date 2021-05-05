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
import org.loose.fis.sre.model.Laundry;
import org.loose.fis.sre.model.Room;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.model.window;
import org.loose.fis.sre.services.LaundryService;
import org.loose.fis.sre.services.RoomService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentsSituationController{
    @FXML
    private TableView paymentsTable;
    @FXML
    private TableColumn<User, String> nameCol;
    @FXML
    private TableColumn<Room, Integer> roomCol;
    @FXML
    private Button backToStudentHomePageButton;

    public void handleBackToStudentHomePageAction() {
        window.goBackWindow("studentPage.fxml",backToStudentHomePageButton);
    }
}

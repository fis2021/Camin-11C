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
import org.loose.fis.sre.model.window;
import org.loose.fis.sre.services.AnnouncementService;
import org.loose.fis.sre.services.LaundryService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LaundryScheduleController implements Initializable{
    @FXML
    private Button backToStudentHomePageButton;
    @FXML
    private TableView<Laundry> laundryTable;
    @FXML
    private TableColumn<Laundry, Integer> roomCol;
    @FXML
    private TableColumn<Laundry, String> dateCol;
    @FXML
    private TableColumn<Laundry, Integer> hourCol;

    public void handleBackToStudentHomePageAction() {
        window.goBackWindow("studentPage.fxml",backToStudentHomePageButton);
    }

    @Override
    public void initialize(URL localation, ResourceBundle resources) {
        ArrayList<Laundry> laundries = new ArrayList<>();
        laundries = LaundryService.getLaundries();
        roomCol.setCellValueFactory(new PropertyValueFactory<>("nrRoom"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("day"));
        hourCol.setCellValueFactory(new PropertyValueFactory<>("hour"));
        ObservableList<Laundry> observableList = FXCollections.observableArrayList(laundries);
        laundryTable.setItems(observableList);
        System.out.println("Showing the laundry");
    }
}
package org.loose.fis.sre.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddPaymentDetailsController {

    @FXML
    private TextField studentNameField;
    @FXML
    private ChoiceBox<String> month;
    @FXML
    private ChoiceBox<String> rentType;
    @FXML
    private ChoiceBox<String> paymentStatus;
    @FXML
    private Button AddPaymentDetailsButton;

    @FXML
    public void initialize() {
        month.getItems().addAll("January", "February", "March", "April", "May", "June", "July",
        "August", "September", "October", "November", "December");
        rentType.getItems().addAll("100RON", "180RON");
        paymentStatus.getItems().addAll("Payed", "Not Payed");
    }

    @FXML
    public void handleAddPaymentDetailsAction() {


    }

}

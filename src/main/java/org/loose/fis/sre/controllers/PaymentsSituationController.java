package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.loose.fis.sre.model.PaymentDetails;
import org.loose.fis.sre.model.window;
import org.loose.fis.sre.services.PaymentDetailsService;
import org.loose.fis.sre.services.UserService;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentsSituationController implements Initializable{
    @FXML
    private TableView paymentsTable;
    @FXML
    private TableColumn<PaymentDetails, String> monthCol;
    @FXML
    private TableColumn<PaymentDetails, String> statusCol;
    @FXML
    private Button backToStudentHomePageButton;

    public void handleBackToStudentHomePageAction() {
        window.goBackWindow("studentPage.fxml",backToStudentHomePageButton);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<PaymentDetails> payments = new ArrayList<>();
        payments = PaymentDetailsService.getPaymentDetails();
        ArrayList<PaymentDetails> my_payments = new ArrayList<>();
        for(PaymentDetails payment: payments){
            if(payment.getStudentName().equals(UserService.getLoggedUser())){
                my_payments.add(payment);
            }
        }
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        ObservableList<PaymentDetails> observableList = FXCollections.observableArrayList(my_payments);
        paymentsTable.setItems(observableList);
    }
}

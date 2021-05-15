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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentPaymentEvidenceController implements Initializable {
    @FXML
    private TableView paymentsTable;
    @FXML
    private TableColumn<PaymentDetails, String> feeCol;
    @FXML
    private TableColumn<PaymentDetails, String> nameCol;
    @FXML
    private Button backToAdminHomePageButton;

    public void handleBackToAdminHomePageAction() {
        window.goBackWindow("adminPage.fxml",backToAdminHomePageButton);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<PaymentDetails> payments = new ArrayList<>();
        payments = PaymentDetailsService.getPaymentDetails();
        ArrayList<PaymentDetails> my_payments = new ArrayList<>();
        String username="";
        for(PaymentDetails payment: payments){
            if(!payment.getStudentName().equals(username)){
                username= payment.getStudentName();
                my_payments.add(payment);
            }
        }
        nameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        feeCol.setCellValueFactory(new PropertyValueFactory<>("fee"));
        ObservableList<PaymentDetails> observableList = FXCollections.observableArrayList(my_payments);
        paymentsTable.setItems(observableList);
    }
}

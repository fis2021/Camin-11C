package org.loose.fis.sre.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class window {
    public static void createWindow(String fxmlName, Button button) throws IOException {
        System.out.println("Student");
        FXMLLoader loader = new FXMLLoader(window.class.getClassLoader().getResource(fxmlName));
        Parent root = loader.load();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

package org.loose.fis.sre.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class window {
    public static void createWindow(String fxmlName, Button button) throws IOException {
        FXMLLoader loader = new FXMLLoader(window.class.getClassLoader().getResource(fxmlName));
        Parent root = loader.load();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void goBackWindow(String fxmlName,Button button){
        try{
            Parent root= FXMLLoader.load(window.class.getClassLoader().getResource(fxmlName));
            Stage stage = (Stage) (button.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}

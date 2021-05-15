package org.loose.fis.sre;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.loose.fis.sre.services.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnnouncementService.initDatabase();
        UserService.initDatabase();
        RoomService.initDatabase();
        LaundryService.initDatabase();
        PaymentDetailsService.initDatabase();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Camin 11C");
        primaryStage.setScene(new Scene(root, 400, 475));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

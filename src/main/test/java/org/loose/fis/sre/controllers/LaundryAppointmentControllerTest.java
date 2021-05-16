package org.loose.fis.sre.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.exceptions.IncorrectLaundryAppointmentException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.LaundryService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;


@ExtendWith(ApplicationExtension.class)
class LaundryAppointmentControllerTest {
    public static final String USERNAME = "password";
    public static final String PASSWORD = "username";

    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
    }

    @BeforeEach
    void setUP() throws IOException, UsernameAlreadyExistsException, IncorrectLaundryAppointmentException {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        LaundryService.initDatabase();
        UserService.addUser(USERNAME, PASSWORD, "Student");
        LaundryService.addLaundry(130, "Monday", 12);
        LaundryService.addLaundry(125, "Friday", 15);
    }

    @Start
    void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Camin 11C");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
    }

    @Test
    void testLaundryAppointmentController(FxRobot robot) {
        robot.clickOn("#username");
        robot.write(USERNAME);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#role");
        robot.type(KeyCode.ENTER);
        robot.clickOn("#loginButton");

        robot.clickOn("#laundryAppointmentButton");
        robot.clickOn("#roomField");
        robot.write("111");
        robot.clickOn("#dayField");
        robot.write("Monday");
        robot.clickOn("#hourField");
        robot.write("16");
        robot.clickOn("#SubmitButton");
        robot.clickOn("#backToStudentHomePageButton");



    }
}
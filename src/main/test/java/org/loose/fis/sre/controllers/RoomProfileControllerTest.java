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
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.RoomService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.loose.fis.sre.exceptions.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class RoomProfileControllerTest {
    public static final String USERNAME = "password";
    public static final String PASSWORD = "username";

    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
    }

    @BeforeEach
    void setUP() throws IOException, UsernameAlreadyExistsException, RoomAlreadyExistsException {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        RoomService.initDatabase();
        UserService.addUser(USERNAME, PASSWORD, "Student");
        RoomService.addRoom(125,1,USERNAME,USERNAME + "doi");
    }

    @Start
    void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Camin 11C");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
    }

    @Test
    void roomServiceTest(FxRobot robot){
        robot.clickOn("#username");
        robot.write(USERNAME);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#role");
        robot.type(KeyCode.ENTER);
        robot.clickOn("#loginButton");

        robot.clickOn("#roomProfileButton");
        robot.clickOn("#backToStudentHomePageButton");
        assertThat(robot.lookup("#backToStudentHomePageButton")).isNotNull();
    }
}
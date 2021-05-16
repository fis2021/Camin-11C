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

import org.loose.fis.sre.exceptions.IncorrectAnnouncementAppointmentException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.AnnouncementService;
import org.loose.fis.sre.services.FileSystemService;

import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class StudentAnnouncementsControllerTest {
    public static final String USERNAME = "password";
    public static final String PASSWORD = "username";

    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
        AnnouncementService.closeDatabase();
    }

    @BeforeEach
    void setUP() throws IOException, UsernameAlreadyExistsException, IncorrectAnnouncementAppointmentException {
        FileSystemService.APPLICATION_FOLDER = ".test-AnnouncementsC";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        AnnouncementService.initDatabase();
        UserService.addUser(USERNAME, PASSWORD, "Student");
        AnnouncementService.addAnnouncement( "See Announcement test");
    }

    @Start
    void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Camin 11C");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
    }

    @Test
    void ShowAnnouncementsTest(FxRobot robot){
        robot.clickOn("#username");
        robot.write(USERNAME);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#role");
        robot.type(KeyCode.ENTER);
        robot.clickOn("#loginButton");

        robot.clickOn("#studentAnnouncementsButton");
        robot.clickOn("#showAnnouncementsButton");
        robot.clickOn("#backToStudentHomePageButton");
        assertThat(robot.lookup("#backToStudentHomePageButton")).isNotNull();
    }
}
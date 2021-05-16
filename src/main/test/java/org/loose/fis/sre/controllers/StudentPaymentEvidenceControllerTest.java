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
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.AnnouncementService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.PaymentDetailsService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class StudentPaymentEvidenceControllerTest {
    public static final String USERNAME = "password";
    public static final String PASSWORD = "username";
    public static final String ADMINUSERNAME = "passwordadmin";

    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
        PaymentDetailsService.closeDatabase();
    }

    @BeforeEach
    void setUP() throws IOException, UsernameAlreadyExistsException {
        FileSystemService.APPLICATION_FOLDER = ".test-PaymentE";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        AnnouncementService.closeDatabase();
        UserService.initDatabase();
        PaymentDetailsService.initDatabase();
        UserService.addUser(ADMINUSERNAME, PASSWORD, "Admin");

    }

    @Start
    void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Camin 11C");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
    }
    @Test
    void PaymentEvidenceTest (FxRobot robot){
        robot.clickOn("#username");
        robot.write(ADMINUSERNAME);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#role");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);
        robot.clickOn("#loginButton");

        robot.clickOn("#studentPaymentEvidenceButton");
        robot.clickOn("#backToAdminHomePageButton");
        robot.clickOn("#adminLogoutButton");
        assertThat(robot.lookup("#adminLogoutButton")).isNotNull();

    }



    }
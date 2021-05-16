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
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.loose.fis.sre.exceptions.*;

import java.io.IOException;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
public class LoginControllerTest {
    public static final String USERNAME = "password";
    public static final String ADMINUSERNAME = "passwordadmin";
    public static final String PASSWORD = "username";

    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
    }

    @BeforeEach
    void setUP() throws IOException, UsernameAlreadyExistsException {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.addUser(USERNAME, PASSWORD, "Student");
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
    void loginTest(FxRobot robot) {
        robot.clickOn("#username");
        robot.write("pass");
        robot.clickOn("#password");
        robot.write("user");
        robot.clickOn("#role");
        robot.type(KeyCode.ENTER);
        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Please enter a valid username!");

        robot.clickOn("#username");
        robot.write("word");
        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText("Wrong Password!");

        robot.clickOn("#role");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);
        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#loginMessage").queryText()).hasText("You selected the wrong role for your user!");

        robot.clickOn("#password");
        robot.write("name");
        robot.clickOn("#role");
        robot.type(KeyCode.UP);
        robot.type(KeyCode.ENTER);
        robot.clickOn("#loginButton");
        assertThat(robot.lookup("#studentLogoutButton")).isNotNull();
        robot.clickOn("#studentLogoutButton");

        robot.clickOn("#username");
        robot.write(ADMINUSERNAME);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#role");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);
        robot.clickOn("#loginButton");

        assertThat(robot.lookup("#adminLogoutButton")).isNotNull();
        robot.clickOn("#adminLogoutButton");

        robot.clickOn("#backToRegisterButton");
        assertThat(robot.lookup("#backToRegisterButton")).isNotNull();
    }
}

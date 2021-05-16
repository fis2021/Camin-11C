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
import org.loose.fis.sre.services.PaymentDetailsService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class PaymentsSituationControllerTest {

        public static final String USERNAME = "password";
        public static final String PASSWORD = "username";

        @AfterEach
        void tearDown() {
            UserService.closeDatabase();
        }

        @BeforeEach
        void setUP() throws IOException, UsernameAlreadyExistsException, IncorrectLaundryAppointmentException {
            FileSystemService.APPLICATION_FOLDER = ".test-paymentSituation";
            FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
            UserService.initDatabase();
            PaymentDetailsService.initDatabase();
            UserService.addUser(USERNAME, PASSWORD, "Student");
            PaymentDetailsService.addPaymentDetails(USERNAME,"100RON","May","payed");
            PaymentDetailsService.addPaymentDetails(USERNAME,"100RON","June","not payed");

        }

        @Start
        void start(Stage primaryStage) throws IOException {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            primaryStage.setTitle("Camin 11C");
            primaryStage.setScene(new Scene(root, 600, 475));
            primaryStage.show();
        }

        @Test
        void testPaymentSituationController(FxRobot robot){
            robot.clickOn("#username");
            robot.write(USERNAME);
            robot.clickOn("#password");
            robot.write(PASSWORD);
            robot.clickOn("#role");
            robot.type(KeyCode.ENTER);
            robot.clickOn("#loginButton");

            robot.clickOn("#checkPaymentsButton");
            assertThat(robot.lookup("#checkPaymentsButton")).isNotNull();
            robot.clickOn("#backToStudentHomePageButton");
            assertThat(robot.lookup("#backToStudentHomePageButton")).isNotNull();

        }

    }

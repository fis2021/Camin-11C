package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.IncorrectLaundryAppointmentException;

import org.loose.fis.sre.model.Laundry;
import org.testfx.api.FxToolkit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LaundryServiceTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All");
    }

    @AfterAll
    static void afterAll() throws TimeoutException {
        FxToolkit.cleanupStages();
    }

    @BeforeEach
    void setUP() throws IOException {
        FileSystemService.APPLICATION_FOLDER = ".test-laundry";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        LaundryService.initDatabase();

    }

    @AfterEach
    void tearDown() {
        LaundryService.closeDatabase();
    }

    @Test
    @DisplayName("Database is initialized, and there are no Laundries")
    void testDataBaseIsInitializedAndNoLaundriesIsPersisted() {
        assertThat(LaundryService.getLaundries()).isNotNull();
        assertThat(LaundryService.getLaundries()).isEmpty();
    }

    @Test
    @DisplayName("Laundry is successfully persisted to Database")
    void testLaundryIsAddedToDatabase() throws IncorrectLaundryAppointmentException {
        LaundryService.addLaundry(100, "Monday", 0);
        assertThat(LaundryService.getLaundries()).isNotEmpty();
        assertThat(LaundryService.getLaundries()).size().isEqualTo(1);
        Laundry Laundry = LaundryService.getLaundries().get(0);
        assertThat(Laundry).isNotNull();
        assertThat(Laundry.getNrRoom()).isEqualTo(100);
        assertThat(Laundry.getDay()).isEqualTo("Monday");
        assertThat(Laundry.getHour()).isEqualTo(0);

    }

    @Test
    @DisplayName("Laundry can not be added twice")
    void testLaundryCanNotBeAddedTwice() {
        assertThrows(IncorrectLaundryAppointmentException.class, () -> {
            LaundryService.addLaundry(100, "Monday", 0);
            LaundryService.addLaundry(100, "Monday", 0);
        });
    }


}
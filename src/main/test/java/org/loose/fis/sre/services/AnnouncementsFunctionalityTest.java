package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.IncorrectAnnouncementAppointmentException;
import org.testfx.api.FxToolkit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnnouncementsFunctionalityTest {
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
        FileSystemService.APPLICATION_FOLDER = ".test-announce";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        AnnouncementService.closeDatabase();
        AnnouncementService.initDatabase();

    }

    @AfterEach
    void tearDown() {
        AnnouncementService.closeDatabase();
    }

    @Test
    @DisplayName("Database is initialized, and there are no Announcements")
    void testDataBaseIsInitializedAndNoAnnouncementsIsPersisted() {
        assertThat(AnnouncementService.getAnnouncements()).isNotNull();
        assertThat(AnnouncementService.getAnnouncements()).isEmpty();
    }

    @Test
    @DisplayName("Announcement is successfully persisted to Database")
    void testAnnouncementIsAddedToDatabase() throws IncorrectAnnouncementAppointmentException {
        AnnouncementService.addAnnouncement("The admin is away for the weekend please be nice");
        assertThat(AnnouncementService.getAnnouncements()).isNotEmpty();
        assertThat(AnnouncementService.getAnnouncements()).size().isEqualTo(1);
        String announcement = AnnouncementService.getAnnouncements().get(0);
        assertThat(announcement).isNotNull();
        assertThat(announcement).isEqualTo("The admin is away for the weekend please be nice");

    }

    @Test
    @DisplayName("Announcement can not be added twice")
    void testAnnouncementCanNotBeAddedTwice() {
        assertThrows(IncorrectAnnouncementAppointmentException.class, () -> {
            AnnouncementService.addAnnouncement("Test Message");
            AnnouncementService.addAnnouncement("Test Message");
        });
    }

}

package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.RoomAlreadyExistsException;
import org.loose.fis.sre.model.Room;
import org.loose.fis.sre.model.User;
import org.testfx.api.FxToolkit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RoomServiceTest {
    public static final String ADMIN = "admin";

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
        FileSystemService.APPLICATION_FOLDER = ".test-room";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        RoomService.initDatabase();

    }

    @AfterEach
    void tearDown() {
        RoomService.closeDatabase();
    }

    @Test
    @DisplayName("Database is initialized, and there are no Rooms")
    void testDataBaseIsInitializedAndNoRoomsIsPersisted() {
        assertThat(RoomService.getRooms()).isNotNull();
        assertThat(RoomService.getRooms()).isEmpty();
    }

    @Test
    @DisplayName("Room is successfully persisted to Database")
    void testRoomIsAddedToDatabase() throws RoomAlreadyExistsException {
        RoomService.addRoom(100, 1, ADMIN, ADMIN);
        assertThat(RoomService.getRooms()).isNotEmpty();
        assertThat(RoomService.getRooms()).size().isEqualTo(1);
        Room Room = RoomService.getRooms().get(0);
        assertThat(Room).isNotNull();
        assertThat(Room.getNrRoom()).isEqualTo(100);
        assertThat(Room.getFloor()).isEqualTo(1);
        assertThat(Room.getStudent1()).isEqualTo(ADMIN);
        assertThat(Room.getStudent2()).isEqualTo(ADMIN);
    }

    @Test
    @DisplayName("Room can not be added twice")
    void testRoomCanNotBeAddedTwice() {
        assertThrows(RoomAlreadyExistsException.class, () -> {
            RoomService.addRoom(100, 1, ADMIN, ADMIN);
            RoomService.addRoom(100, 1, ADMIN, ADMIN);
        });
    }

}
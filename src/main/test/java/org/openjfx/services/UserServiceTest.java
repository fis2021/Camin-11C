package org.openjfx.services;


import org.junit.jupiter.api.*;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

class UserServiceTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After All");
    }

//    @BeforeEach
//    void setUp() throws Exception {
//        FileSystemService.APPLICATION_FOLDER = ".test-registration-database";
//        FileSystemService.initDirectory();
//        UserService.initDatabase();
//    }

    @Test
    @DisplayName("Database is initialized, and there are no users")
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.getUsers()).isNotNull();
        assertThat(UserService.getUsers()).isEmpty();
    }

    @Test
    void clientIsAddedToDatabase() {
        UserService.addUser("user1","user1","Client");
        assertThat(UserService.getUsers()).size().isEqualTo(1);
        User user1 = UserService.getUsers().get(0);
        assertThat(user1).isNotNull();
        assertThat(user1.getUsername()).isEqualTo("user1");
        assertThat(user1.getPassword()).isEqualTo(UserService.encodePassword("user1","user1"));
        assertThat(user1.getRole()).isEqualTo("Client");
    }

}
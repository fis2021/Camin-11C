package org.loose.fis.sre.services;


import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;
import org.loose.fis.sre.exceptions.*;
import org.testfx.api.FxToolkit;

class UserServiceTest {

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
        FileSystemService.APPLICATION_FOLDER = ".test-user";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();

    }

    @AfterEach
    void tearDown(){
        UserService.closeDatabase();
    }
    @Test
    @DisplayName("Database is initialized, and there are no users")
    void testDataBaseIsInitializedAndNoUsersIsPersisted(){
        assertThat(UserService.getUsers()).isNotNull();
        assertThat(UserService.getUsers()).isEmpty();
    }

    @Test
    @DisplayName("User is successfully persisted to Database")
    void testUserIsAddedToDatabase() throws UsernameAlreadyExistsException {
        UserService.addUser(ADMIN, ADMIN, ADMIN);
        assertThat(UserService.getUsers()).isNotEmpty();
        assertThat(UserService.getUsers()).size().isEqualTo(1);
        User user = UserService.getUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(ADMIN);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(ADMIN,ADMIN));
        assertThat(user.getRole()).isEqualTo(ADMIN);
    }

    @Test
    @DisplayName("User can not be added twice")
    void testUserCanNotBeAddedTwice(){
        assertThrows(UsernameAlreadyExistsException.class, () ->{
            UserService.addUser(ADMIN, ADMIN, ADMIN);
            UserService.addUser(ADMIN, ADMIN, ADMIN);
        });
    }

    @Test
    @DisplayName("Password encoding")
    public void testPasswordEncoding() {
        assertNotEquals(ADMIN, UserService.encodePassword("username", ADMIN));
    }

    @Test
    @DisplayName("Verify if login is correct")
    public void testIsLoginCorrect() throws UsernameAlreadyExistsException {
        UserService.addUser(ADMIN, ADMIN, ADMIN);
        assertEquals(true,UserService.isLoginCorrect(ADMIN,ADMIN,ADMIN));
    }

    @Test
    @DisplayName("Verify if equals works correctly")
    void testEqualsIsWorkingCorrectly(){
        assertEquals(true,((new User("user","password","Student")).
                equals(new User("user","password","Student"))));
        assertNotEquals(true,((new User("user","password","Student")).
                equals(new User("user12","password","Student"))));
        assertNotEquals(true,((new User("user","password","Student")).
                equals(new User("user","password12","Student"))));
    }
}
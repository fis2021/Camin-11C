package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;
import org.loose.fis.sre.exceptions.*;

public class UserService {

    private static ObjectRepository<User> userRepository;
    private static String loggedUser;
    private static Nitrite database;
    public static void initDatabase() {
        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }

    public static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static boolean isUsernameTaken(String username){
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkWrongRole(String username,String role) throws UsernameDoesNotExistException {
        for(User user: userRepository.find()){
            if(Objects.equals(username, user.getUsername())){
                if(Objects.equals(role,user.getRole())){
                    return true;
                } else {
                    return false;
                }
            }
        }
        throw new UsernameDoesNotExistException(username);
    }

    public static boolean isLoginCorrect(String username,String password,String role){
        for (User user: userRepository.find()){
            if(username.equals(user.getUsername()) &&
                    encodePassword(username,password).equals(user.getPassword()) &&
                    role.equals(user.getRole())){
                loggedUser = user.getUsername();
                return true;
            }
        }
        return false;
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();
        for (User user: UserService.getUserRepository().find()) {
            users.add(user);
        }
        return users;
    }

    public static ObjectRepository<User> getUserRepository() {
        return userRepository;
    }

    public static String getLoggedUser() {
        return loggedUser;
    }

    public static void closeDatabase(){
        database.close();
    }
}

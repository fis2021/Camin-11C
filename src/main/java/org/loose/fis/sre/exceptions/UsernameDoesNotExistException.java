package org.loose.fis.sre.exceptions;

public class UsernameDoesNotExistException extends Exception{
    private String username;
    public UsernameDoesNotExistException(String username){
        super(String.format("The username %s does not exist!", username));
        this.username = username;

    }
}

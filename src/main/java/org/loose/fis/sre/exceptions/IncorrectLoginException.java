package org.loose.fis.sre.exceptions;

public class IncorrectLoginException extends Exception{
    private String password;

    public IncorrectLoginException(String password){
        super("Username already taken or incorrect password");
        this.password = password;
    }

}

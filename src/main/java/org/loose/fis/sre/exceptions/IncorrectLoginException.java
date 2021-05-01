package org.loose.fis.sre.exceptions;

public class IncorrectLoginException extends Exception{
    private String password;

    public IncorrectLoginException(String text){
        super(text);
        this.password = password;
    }

}

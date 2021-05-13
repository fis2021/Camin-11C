package org.loose.fis.sre.exceptions;

public class RoomAlreadyExistsException extends Exception{
    private int nrRoom;

    public RoomAlreadyExistsException(int nrRoom) {
        super(String.format("A room with the number %d already exists!", nrRoom));
        this.nrRoom = nrRoom;
    }
}

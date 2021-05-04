package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;
import org.loose.fis.sre.exceptions.IncorrectLoginException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Room {
    @Id
    private int nrRoom;
    private int floor;
    private User student1;
    private User student2;
    public Room(int nrRoom, int floor, User student1, User student2) {
        this.nrRoom = nrRoom;
        this.floor = floor;
        this.student1 = student1;
        this.student2 = student2;
    }

    public Room() {
    }

    public int getNrRoom() {
        return nrRoom;
    }

    public int getFloor() {
        return floor;
    }

    public User getStudent1() {
        return student1;
    }

    public User getStudent2() {
        return student2;
    }
}

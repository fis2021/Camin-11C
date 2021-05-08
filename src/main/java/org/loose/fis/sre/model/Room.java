package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;
import org.loose.fis.sre.exceptions.IncorrectLoginException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Room {
    @Id
    private int nrRoom;
    private int floor;
    private String student1;
    private String student2;
    public Room(int nrRoom, int floor, String student1, String student2) {
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

    public String getStudent1() {
        return student1;
    }

    public String getStudent2() {
        return student2;
    }
}

package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class Laundry {
    @Id
    private int nrRoom;
    private String day;
    private int hour;

    public Laundry(int nrRoom,String day,int hour){
        this.nrRoom = nrRoom;
        this.day = day;
        this.hour = hour;
    }
    public Laundry(){}

    public int getNrRoom() {
        return nrRoom;
    }

    public String getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }
}

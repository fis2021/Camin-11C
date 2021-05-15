package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class Evidence {
    @Id
    private String name;
    private int room;

    public Evidence(String name,int room){
        this.name = name;
        this.room = room;
    }
    public Evidence(){}

    public String getName() {
        return name;
    }

    public int getRoom() {
        return room;
    }

}

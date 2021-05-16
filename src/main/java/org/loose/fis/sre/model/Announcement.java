package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class Announcement {
    @Id
    private String announcement;

    public Announcement(String announcement) {
        this.announcement = announcement;
    }

    public Announcement() {

    }

    public String getAnnouncement() {
        return announcement;
    }
}

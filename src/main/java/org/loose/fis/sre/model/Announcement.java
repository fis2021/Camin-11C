package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;
import org.loose.fis.sre.services.AnnouncementService;
import org.loose.fis.sre.services.UserService;

import java.util.ArrayList;
import java.util.Objects;

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

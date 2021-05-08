package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Announcement;

import java.util.ArrayList;

public class AnnouncementService {
    private static ObjectRepository<Announcement> announcementRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("announcement.db").toFile())
                .openOrCreate("test", "test");

        announcementRepository = database.getRepository(Announcement.class);
    }

    public static void addAnnouncement(String announcement){
        announcementRepository.insert(new Announcement(announcement));
    }

    public static ArrayList<String> getAnnouncements(){
        ArrayList<String> announcements = new ArrayList<>();
        for (Announcement announcement: AnnouncementService.getAnnouncementRepository().find()) {
            announcements.add(announcement.getAnnouncement());
        }
        return announcements;
    }

    public static ObjectRepository<Announcement> getAnnouncementRepository() {
        return announcementRepository;
    }
}

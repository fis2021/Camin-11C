package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.IncorrectAnnouncementAppointmentException;
import org.loose.fis.sre.exceptions.IncorrectLaundryAppointmentException;
import org.loose.fis.sre.model.Announcement;
import org.loose.fis.sre.model.Laundry;

import java.util.ArrayList;

public class AnnouncementService {
    private static ObjectRepository<Announcement> announcementRepository;
    private static Nitrite database;
    public static void initDatabase() {
        FileSystemService.initDirectory();
         database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("announcement.db").toFile())
                .openOrCreate("test", "test");

        announcementRepository = database.getRepository(Announcement.class);
    }

    public static void addAnnouncement(String announcement) throws IncorrectAnnouncementAppointmentException {
        isAnnouncementCorrect(announcement);
        announcementRepository.insert(new Announcement(announcement));
    }

    public static void isAnnouncementCorrect(String announce) throws IncorrectAnnouncementAppointmentException {
        for (Announcement announcement : announcementRepository.find()) {
            if (announce.equals(announcement.getAnnouncement())) {
                throw new IncorrectAnnouncementAppointmentException("This appointment was already taken");
            }
        }

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

    public static void closeDatabase()
    {
        database.close();
    }
}

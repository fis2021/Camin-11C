package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.IncorrectLaundryAppointmentException;
import org.loose.fis.sre.exceptions.RoomAlreadyExistsException;
import org.loose.fis.sre.model.Laundry;
import org.loose.fis.sre.model.Room;
import org.loose.fis.sre.model.User;

import java.util.ArrayList;
import java.util.Objects;

public class LaundryService {
    private static ObjectRepository<Laundry> laundryRepository;
    private static Nitrite database;
    public static void initDatabase() {
        FileSystemService.initDirectory();
         database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("laundry.db").toFile())
                .openOrCreate("laundry", "1234");

        laundryRepository = database.getRepository(Laundry.class);
    }

    public static void addLaundry(int nrRoom, String day, int hour) throws IncorrectLaundryAppointmentException {
        isAppointmentCorrect(nrRoom,day,hour);
        laundryRepository.insert(new Laundry(nrRoom, day, hour));
    }

    public static ObjectRepository<Laundry> getLaundryRepository() {
        return laundryRepository;
    }

    public static ArrayList<Laundry> getLaundries() {
        ArrayList<Laundry> laundries = new ArrayList<>();
        for (Laundry laundry : LaundryService.getLaundryRepository().find()) {
            laundries.add(laundry);
        }
        return laundries;
    }

    public static void isAppointmentCorrect(int nrRoom, String day, int hour) throws IncorrectLaundryAppointmentException {
        for (Laundry laundry : laundryRepository.find()) {
            if (nrRoom == laundry.getNrRoom() &&
                    day.equals(laundry.getDay()) &&
                    hour == laundry.getHour()) {
            throw new IncorrectLaundryAppointmentException("This appointment was already taken");
            }


        }

    }

    public static void closeDatabase()
    {
        database.close();
    }
}

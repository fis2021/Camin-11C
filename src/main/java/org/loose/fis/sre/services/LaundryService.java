package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Laundry;

import java.util.ArrayList;

public class LaundryService {
    private static ObjectRepository<Laundry> laundryRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("laundry.db").toFile())
                .openOrCreate("laundry", "1234");

        laundryRepository = database.getRepository(Laundry.class);
    }

    public static void addLaundry(int nrRoom,String day,int hour){
        laundryRepository.insert(new Laundry(nrRoom,day,hour));
    }

    public static ObjectRepository<Laundry> getLaundryRepository() {
        return laundryRepository;
    }

    public static ArrayList<Laundry> getLaundries(){
        ArrayList<Laundry> laundries = new ArrayList<>();
        for (Laundry laundry: LaundryService.getLaundryRepository().find()) {
            laundries.add(laundry);
        }
        return laundries;
    }
}

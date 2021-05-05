package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Announcement;
import org.loose.fis.sre.model.Room;

import java.util.ArrayList;

public class RoomService {
    private static ObjectRepository<Room> roomRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("room.db").toFile())
                .openOrCreate("test", "test");

        roomRepository = database.getRepository(Room.class);
    }

    public static void addRoom(int nrRoom, int floor, String student1, String student2){
        roomRepository.insert(new Room(nrRoom,floor,student1,student2));
    }

    public static ArrayList<Room> getRooms(){
        ArrayList<Room> rooms = new ArrayList<>();
        for (Room room: RoomService.getRoomRepository().find()) {
            rooms.add(room);
        }
        return rooms;
    }

    public static ObjectRepository<Room> getRoomRepository() {
        return roomRepository;
    }
}

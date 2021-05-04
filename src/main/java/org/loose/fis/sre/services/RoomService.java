package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Room;
import org.loose.fis.sre.model.User;

public class RoomService {
    private static ObjectRepository<Room> roomRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("room.db").toFile())
                .openOrCreate("test", "test");

        roomRepository = database.getRepository(Room.class);
    }

    public static void addRoom(int nrRoom, int floor, User student1, User student2){
        roomRepository.insert(new Room(nrRoom,floor,student1,student2));
    }

    public static ObjectRepository<Room> getRoomRepository() {
        return roomRepository;
    }
}

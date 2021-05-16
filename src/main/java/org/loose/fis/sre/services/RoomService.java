package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.RoomAlreadyExistsException;
import org.loose.fis.sre.model.Room;
import org.loose.fis.sre.model.User;

import java.util.ArrayList;
import java.util.Objects;

public class RoomService {
    private static ObjectRepository<Room> roomRepository;
    private static Nitrite database;

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(FileSystemService.getPathToFile("room.db").toFile())
                .openOrCreate("test", "test");

        roomRepository = database.getRepository(Room.class);
    }

    public static void addRoom(int nrRoom, int floor, String student1, String student2) throws RoomAlreadyExistsException{
        checkRoomDoesNotAlreadyExist(nrRoom);
        roomRepository.insert(new Room(nrRoom,floor,student1,student2));
    }

    private static void checkRoomDoesNotAlreadyExist(int nrRoom) throws RoomAlreadyExistsException {
        for (Room room : roomRepository.find()) {
            if (Objects.equals(nrRoom, room.getNrRoom()))
                throw new RoomAlreadyExistsException(nrRoom);
        }
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

    public static void closeDatabase(){
        database.close();
    }
}

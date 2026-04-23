package com.smartcampus.repository;

import com.smartcampus.model.Room;

import java.util.*;

// handles room data (in-memory)
public class RoomRepository {

    // store rooms
    private static Map<Integer, Room> rooms = new HashMap<>();

    // id counter
    private static int currentId = 3;

    // sample data
    static {
        rooms.put(1, new Room(1, "Lab A", 40));
        rooms.put(2, new Room(2, "Lecture Hall", 100));
    }

    // get all rooms
    public static Collection<Room> getAllRooms() {
        return rooms.values();
    }

    // get room by id
    public static Room getRoomById(int id) {
        return rooms.get(id);
    }

    // add new room
    public static Room addRoom(Room room) {
        room.setId(currentId++);
        rooms.put(room.getId(), room);
        return room;
    }

    // update room
    public static Room updateRoom(int id, Room updatedRoom) {
        Room existing = rooms.get(id);

        if (existing != null) {
            existing.setName(updatedRoom.getName());
            existing.setCapacity(updatedRoom.getCapacity());
        }

        return existing;
    }

    // delete room
    public static Room deleteRoom(int id) {
        return rooms.remove(id);
    }
}
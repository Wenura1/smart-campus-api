package com.smartcampus.repository;

import com.smartcampus.model.Room;

import java.util.ArrayList;
import java.util.List;

// this class stores room data in memory
public class RoomRepository {

    // list to store rooms
    private static List<Room> rooms = new ArrayList<>();

    // static block to add some sample data
    static {
        rooms.add(new Room(1, "Lab A", 40));
        rooms.add(new Room(2, "Lecture Hall", 100));
    }

    // get all rooms
    public static List<Room> getAllRooms() {
        return rooms;
    }

    // get room by id
    public static Room getRoomById(int id) {
        for (Room room : rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        return null;
    }

    // add new room
    public static void addRoom(Room room) {
        rooms.add(room);
    }

    // update room
    public static boolean updateRoom(int id, Room updatedRoom) {
        for (Room room : rooms) {
            if (room.getId() == id) {
                room.setName(updatedRoom.getName());
                room.setCapacity(updatedRoom.getCapacity());
                return true;
            }
        }
        return false;
    }

    // delete room
    public static boolean deleteRoom(int id) {
        return rooms.removeIf(room -> room.getId() == id);
    }
}
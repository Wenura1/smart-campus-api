package com.smartcampus.resources;

import com.smartcampus.model.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/rooms")
public class RoomResource {

    // simple in-memory storage
    private static Map<Integer, Room> rooms = new HashMap<>();

    // GET all rooms
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Integer, Room> getAllRooms() {
        return rooms;
    }

    // GET room by id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Room getRoom(@PathParam("id") int id) {
        return rooms.get(id);
    }

    // POST create room
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addRoom(Room room) {
        rooms.put(room.getId(), room);
        return "Room added successfully";
    }

    // PUT update room
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateRoom(@PathParam("id") int id, Room updatedRoom) {

        if (!rooms.containsKey(id)) {
            return "Room not found";
        }

        updatedRoom.setId(id);
        rooms.put(id, updatedRoom);

        return "Room updated successfully";
    }

    // DELETE room
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteRoom(@PathParam("id") int id) {

        if (!rooms.containsKey(id)) {
            return "Room not found";
        }

        rooms.remove(id);

        return "Room deleted successfully";
    }
}
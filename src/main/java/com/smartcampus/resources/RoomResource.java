package com.smartcampus.resources;

import com.smartcampus.model.Room;
import com.smartcampus.repository.RoomRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

// endpoint → /api/v1/rooms
@Path("/rooms")
public class RoomResource {

    // GET all rooms
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms() {
        return RoomRepository.getAllRooms();
    }

    // GET room by id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Room getRoom(@PathParam("id") int id) {
        return RoomRepository.getRoomById(id);
    }

    // POST new room
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addRoom(Room room) {
        RoomRepository.addRoom(room);
        return "Room added";
    }

    // PUT update room
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateRoom(@PathParam("id") int id, Room room) {

        boolean updated = RoomRepository.updateRoom(id, room);

        if (updated) {
            return "Room updated";
        } else {
            return "Room not found";
        }
    }

    // DELETE room
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteRoom(@PathParam("id") int id) {

        boolean deleted = RoomRepository.deleteRoom(id);

        if (deleted) {
            return "Room deleted";
        } else {
            return "Room not found";
        }
    }
}
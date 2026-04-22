package com.smartcampus.resources;

import com.smartcampus.model.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

// API for room operations
@Path("/rooms")
public class RoomResource {

    // in-memory storage
    private static Map<Integer, Room> rooms = new HashMap<>();

    // auto id counter
    private static int currentId = 3;

    // sample data
    static {
        rooms.put(1, new Room(1, "Lab A", 40));
        rooms.put(2, new Room(2, "Lecture Hall", 100));
    }

    // get all rooms
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Room> getAllRooms() {
        return rooms.values();
    }

    // get room by id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoom(@PathParam("id") int id) {

        Room room = rooms.get(id);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Room not found")
                    .build();
        }

        return Response.ok(room).build();
    }

    // add new room (id auto generated)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoom(Room room) {

        // validation
        if (room.getName() == null || room.getName().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Room name required")
                    .build();
        }

        if (room.getCapacity() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Capacity must be > 0")
                    .build();
        }

        // generate id
        room.setId(currentId++);
        rooms.put(room.getId(), room);

        return Response.status(Response.Status.CREATED)
                .entity(room)
                .build();
    }

    // update room
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRoom(@PathParam("id") int id, Room updatedRoom) {

        Room existing = rooms.get(id);

        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Room not found")
                    .build();
        }

        // validation
        if (updatedRoom.getName() == null || updatedRoom.getName().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Room name required")
                    .build();
        }

        if (updatedRoom.getCapacity() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Capacity must be > 0")
                    .build();
        }

        existing.setName(updatedRoom.getName());
        existing.setCapacity(updatedRoom.getCapacity());

        return Response.ok(existing).build();
    }

    // delete room
    @DELETE
    @Path("/{id}")
    public Response deleteRoom(@PathParam("id") int id) {

        Room removed = rooms.remove(id);

        if (removed == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Room not found")
                    .build();
        }

        return Response.ok("Room deleted").build();
    }
}
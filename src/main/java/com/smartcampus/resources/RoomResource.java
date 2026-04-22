package com.smartcampus.resources;

import com.smartcampus.model.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

// base path → /api/v1/rooms
@Path("/rooms")
public class RoomResource {

    // in-memory storage
    private static Map<Integer, Room> rooms = new HashMap<>();

    // sample data
    static {
        rooms.put(1, new Room(1, "Lab A", 40));
        rooms.put(2, new Room(2, "Lecture Hall", 100));
    }

    // GET all rooms
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Room> getAllRooms() {
        return rooms.values();
    }

    // GET room by id
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

    // POST new room
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoom(Room room) {

        rooms.put(room.getId(), room);

        return Response.status(Response.Status.CREATED)
                .entity(room)
                .build();
    }

    // PUT update room
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

        // update values
        existing.setName(updatedRoom.getName());
        existing.setCapacity(updatedRoom.getCapacity());

        return Response.ok(existing).build();
    }

    // DELETE room
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
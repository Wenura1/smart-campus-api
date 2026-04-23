package com.smartcampus.resources;

import com.smartcampus.model.Room;
import com.smartcampus.model.Sensor;
import com.smartcampus.exception.RoomNotEmptyException;

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

    // helper method (standard response format)
    private Map<String, Object> buildResponse(String status, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("data", data);
        return response;
    }

    // get all rooms
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRooms() {
        return Response.ok(buildResponse("success", rooms.values())).build();
    }

    // get room by id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoom(@PathParam("id") int id) {

        Room room = rooms.get(id);

        // 404 when not found
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(buildResponse("error", "Room not found"))
                    .build();
        }

        return Response.ok(buildResponse("success", room)).build();
    }

    // add new room
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRoom(Room room) {

        // validation
        if (room == null || room.getName() == null || room.getName().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(buildResponse("error", "Room name required"))
                    .build();
        }

        if (room.getCapacity() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(buildResponse("error", "Capacity must be > 0"))
                    .build();
        }

        // generate id
        room.setId(currentId++);
        rooms.put(room.getId(), room);

        return Response.status(Response.Status.CREATED)
                .entity(buildResponse("success", room))
                .build();
    }

    // update room
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRoom(@PathParam("id") int id, Room updatedRoom) {

        Room existing = rooms.get(id);

        // 404 when not found
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(buildResponse("error", "Room not found"))
                    .build();
        }

        // validation
        if (updatedRoom == null || updatedRoom.getName() == null || updatedRoom.getName().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(buildResponse("error", "Room name required"))
                    .build();
        }

        if (updatedRoom.getCapacity() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(buildResponse("error", "Capacity must be > 0"))
                    .build();
        }

        // update values
        existing.setName(updatedRoom.getName());
        existing.setCapacity(updatedRoom.getCapacity());

        return Response.ok(buildResponse("success", existing)).build();
    }

    // delete room
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRoom(@PathParam("id") int id) {

        Room room = rooms.get(id);

        // 404 when not found
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(buildResponse("error", "Room not found"))
                    .build();
        }

        // check if any sensor is using this room
        for (Sensor sensor : SensorResource.getSensorMap().values()) {
            if (sensor.getRoomId() == id) {
                // correct: 409 conflict (handled by exception mapper)
                throw new RoomNotEmptyException("Room has sensors assigned");
            }
        }

        rooms.remove(id);

        return Response.ok(buildResponse("success", room)).build();
    }

    // expose rooms (used by SensorResource)
    public static Map<Integer, Room> getRoomMap() {
        return rooms;
    }
}
package com.smartcampus.resources;

import com.smartcampus.model.Room;
import com.smartcampus.model.Sensor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

// API for sensors
@Path("/sensors")
public class SensorResource {

    // in-memory storage
    private static Map<Integer, Sensor> sensors = new HashMap<>();

    // id generator
    private static int currentId = 1;

    // simple response format
    private Map<String, Object> buildResponse(String status, Object data) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", status);
        res.put("data", data);
        return res;
    }

    // GET all sensors
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSensors() {
        return Response.ok(buildResponse("success", sensors.values())).build();
    }

    // POST new sensor
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSensor(Sensor sensor) {

        // basic validation
        if (sensor == null || sensor.getType() == null || sensor.getType().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(buildResponse("error", "Sensor type required"))
                    .build();
        }

        // check room exists (important for coursework)
        Room room = RoomResource.getRoomMap().get(sensor.getRoomId());

        if (room == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(buildResponse("error", "Room does not exist"))
                    .build();
        }

        // create sensor
        sensor.setId(currentId++);
        sensors.put(sensor.getId(), sensor);

        return Response.status(Response.Status.CREATED)
                .entity(buildResponse("success", sensor))
                .build();
    }

    // expose sensors map (used later)
    public static Map<Integer, Sensor> getSensorMap() {
        return sensors;
    }
}
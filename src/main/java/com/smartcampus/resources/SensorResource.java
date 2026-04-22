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

    // helper response format
    private Map<String, Object> buildResponse(String status, Object data) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", status);
        res.put("data", data);
        return res;
    }

    // get all sensors (with optional filtering)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSensors(@QueryParam("type") String type) {

        Collection<Sensor> result = sensors.values();

        // filter by type if provided
        if (type != null && !type.trim().isEmpty()) {

            List<Sensor> filtered = new ArrayList<>();

            for (Sensor s : sensors.values()) {
                if (s.getType().equalsIgnoreCase(type)) {
                    filtered.add(s);
                }
            }

            result = filtered;
        }

        return Response.ok(buildResponse("success", result)).build();
    }

    // add new sensor
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSensor(Sensor sensor) {

        // validation
        if (sensor == null || sensor.getType() == null || sensor.getType().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(buildResponse("error", "Sensor type required"))
                    .build();
        }

        // check room exists
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

    // sub-resource locator for readings
    @Path("/{id}/readings")
    public SensorReadingResource getReadingResource(@PathParam("id") int id) {
        return new SensorReadingResource(id);
    }

    // expose sensors (used later)
    public static Map<Integer, Sensor> getSensorMap() {
        return sensors;
    }
}
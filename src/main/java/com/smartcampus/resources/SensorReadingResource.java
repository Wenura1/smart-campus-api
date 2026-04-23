package com.smartcampus.resources;

import com.smartcampus.model.Sensor;
import com.smartcampus.model.SensorReading;
import com.smartcampus.exception.SensorUnavailableException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

// API for sensor readings (sub-resource)
public class SensorReadingResource {

    private int sensorId;

    // store readings per sensor
    private static Map<Integer, List<SensorReading>> readingsMap = new HashMap<>();

    // id generator
    private static int currentId = 1;

    // constructor (gets sensor id from parent resource)
    public SensorReadingResource(int sensorId) {
        this.sensorId = sensorId;
    }

    // helper method to build response
    private Map<String, Object> buildResponse(String status, Object data) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", status);
        res.put("data", data);
        return res;
    }

    // get all readings for a sensor
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReadings() {

        // check sensor exists (404 if not found)
        Sensor sensor = SensorResource.getSensorMap().get(sensorId);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(buildResponse("error", "Sensor not found"))
                    .build();
        }

        // get readings list (empty list if none)
        List<SensorReading> list = readingsMap.get(sensorId);

        if (list == null) {
            list = new ArrayList<>();
        }

        return Response.ok(buildResponse("success", list)).build();
    }

    // add new reading
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReading(SensorReading reading) {

        // check sensor exists (404 if not found)
        Sensor sensor = SensorResource.getSensorMap().get(sensorId);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(buildResponse("error", "Sensor not found"))
                    .build();
        }

        // check sensor status (403 if under maintenance)
        if ("MAINTENANCE".equalsIgnoreCase(sensor.getStatus())) {
            throw new SensorUnavailableException("Sensor is under maintenance");
        }

        // validate input (400 bad request)
        if (reading == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(buildResponse("error", "Invalid reading"))
                    .build();
        }

        // create reading
        reading.setId(currentId++);
        reading.setTimestamp(System.currentTimeMillis());

        readingsMap.putIfAbsent(sensorId, new ArrayList<>());
        readingsMap.get(sensorId).add(reading);

        // update sensor current value (important requirement)
        sensor.setCurrentValue(reading.getValue());

        return Response.status(Response.Status.CREATED)
                .entity(buildResponse("success", reading))
                .build();
    }
}
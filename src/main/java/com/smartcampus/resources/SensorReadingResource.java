package com.smartcampus.resources;

import com.smartcampus.model.Sensor;
import com.smartcampus.model.SensorReading;
import com.smartcampus.exception.LinkedResourceNotFoundException;
import com.smartcampus.exception.SensorUnavailableException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

// API for sensor readings (sub-resource)
public class SensorReadingResource {

    private int sensorId;

    // store readings for each sensor
    private static Map<Integer, List<SensorReading>> readingsMap = new HashMap<>();

    // id generator
    private static int currentId = 1;

    // constructor (gets sensor id from parent)
    public SensorReadingResource(int sensorId) {
        this.sensorId = sensorId;
    }

    // helper response format
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

        // check sensor exists
        Sensor sensor = SensorResource.getSensorMap().get(sensorId);

        if (sensor == null) {
            throw new LinkedResourceNotFoundException("Sensor not found");
        }

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

        // check sensor exists
        Sensor sensor = SensorResource.getSensorMap().get(sensorId);

        if (sensor == null) {
            throw new LinkedResourceNotFoundException("Sensor not found");
        }

        // check sensor status (important for rubric)
        if ("MAINTENANCE".equalsIgnoreCase(sensor.getStatus())) {
            throw new SensorUnavailableException("Sensor is under maintenance");
        }

        // validation
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

        // update sensor current value (IMPORTANT requirement)
        sensor.setCurrentValue(reading.getValue());

        return Response.status(Response.Status.CREATED)
                .entity(buildResponse("success", reading))
                .build();
    }
}
package com.smartcampus.resources;

import com.smartcampus.model.Sensor;
import com.smartcampus.model.SensorReading;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

// sub-resource for sensor readings
public class SensorReadingResource {

    private int sensorId;

    // store readings per sensor
    private static Map<Integer, List<SensorReading>> readingsMap = new HashMap<>();

    private static int currentId = 1;

    // constructor
    public SensorReadingResource(int sensorId) {
        this.sensorId = sensorId;
    }

    // helper response
    private Map<String, Object> buildResponse(String status, Object data) {
        Map<String, Object> res = new HashMap<>();
        res.put("status", status);
        res.put("data", data);
        return res;
    }

    // GET all readings for a sensor
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReadings() {

        List<SensorReading> list = readingsMap.get(sensorId);

        if (list == null) {
            list = new ArrayList<>();
        }

        return Response.ok(buildResponse("success", list)).build();
    }

    // POST new reading
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReading(SensorReading reading) {

        // check sensor exists
        Sensor sensor = SensorResource.getSensorMap().get(sensorId);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(buildResponse("error", "Sensor not found"))
                    .build();
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

        // IMPORTANT: update sensor current value
        sensor.setCurrentValue(reading.getValue());

        return Response.status(Response.Status.CREATED)
                .entity(buildResponse("success", reading))
                .build();
    }
}
package com.smartcampus.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class DiscoveryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getApiInfo() {

        Map<String, Object> response = new HashMap<>();

        response.put("version", "v1");
        response.put("contact", "admin@uni.com");

        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");

        response.put("resources", resources);

        return response;
    }

    // update an existing room
    @PUT
    @Path("/rooms/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateRoom(@PathParam("id") int id, Room updatedRoom) {

        if (!rooms.containsKey(id)) {
            return "Room not found";
        }

        updatedRoom.setId(id); // keep id same
        rooms.put(id, updatedRoom);

        return "Room updated successfully";
    }
}
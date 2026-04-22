package com.smartcampus.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

// base endpoint for API info
// when user hits /api/v1 -> this runs
@Path("/")
public class DiscoveryResource {

    // handles GET request
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getApiInfo() {

        // main response object
        Map<String, Object> response = new HashMap<>();

        // basic API details
        response.put("version", "v1");
        response.put("contact", "admin@uni.com");

        // list of available endpoints
        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms"); // rooms API

        // attach resources to main response
        response.put("resources", resources);

        // return JSON response
        return response;
    }
}
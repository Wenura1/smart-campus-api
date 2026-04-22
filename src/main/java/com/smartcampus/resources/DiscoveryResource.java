package com.smartcampus.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

// endpoint → /api/v1/info
@Path("/info")
public class DiscoveryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getApiInfo() {

        // response map
        Map<String, Object> response = new HashMap<>();

        // basic info
        response.put("version", "v1");
        response.put("contact", "admin@uni.com");

        // endpoints list
        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");

        response.put("resources", resources);

        return response;
    }
}
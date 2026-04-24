package com.smartcampus.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

// root API endpoint
@Path("/")
public class InfoResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfo() {

        Map<String, Object> response = new HashMap<>();

        response.put("contact", "admin@uni.com");

        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");

        response.put("resources", resources);
        response.put("version", "v1");

        return Response.ok(response).build();
    }
}
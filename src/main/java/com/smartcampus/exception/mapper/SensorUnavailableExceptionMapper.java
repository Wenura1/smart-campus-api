package com.smartcampus.exception.mapper;

import com.smartcampus.exception.SensorUnavailableException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

// maps sensor unavailable to 403
@Provider
public class SensorUnavailableExceptionMapper implements ExceptionMapper<SensorUnavailableException> {

    @Override
    public Response toResponse(SensorUnavailableException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("data", ex.getMessage());

        return Response.status(Response.Status.FORBIDDEN)
                .entity(response)
                .build();
    }
}
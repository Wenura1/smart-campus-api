package com.smartcampus.exception.mapper;

import com.smartcampus.exception.LinkedResourceNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

// maps invalid linked resource to 422
@Provider
public class LinkedResourceNotFoundExceptionMapper implements ExceptionMapper<LinkedResourceNotFoundException> {

    @Override
    public Response toResponse(LinkedResourceNotFoundException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("data", ex.getMessage());

        return Response.status(422)
                .entity(response)
                .build();
    }
}
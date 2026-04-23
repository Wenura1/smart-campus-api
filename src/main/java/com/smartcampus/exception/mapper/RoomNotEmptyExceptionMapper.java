package com.smartcampus.exception.mapper;

import com.smartcampus.exception.RoomNotEmptyException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

// maps room not empty error to 409
@Provider
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {

    @Override
    public Response toResponse(RoomNotEmptyException ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("data", ex.getMessage());

        return Response.status(Response.Status.CONFLICT)
                .entity(response)
                .build();
    }
}
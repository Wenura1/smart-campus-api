package com.smartcampus.config;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

// logs incoming requests and responses
@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    // log request
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println("Request: " +
                requestContext.getMethod() + " " +
                requestContext.getUriInfo().getPath());
    }

    // log response
    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        System.out.println("Response: " +
                responseContext.getStatus());
    }
}
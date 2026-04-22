package com.smartcampus;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {

    // base URL
    public static final String BASE_URI = "http://localhost:8080/";

    public static HttpServer startServer() {

        // scan resources
        final ResourceConfig rc = new ResourceConfig()
                .packages("com.smartcampus");

        // set base path here (/api/v1)
        return GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI + "api/v1/"), rc
        );
    }

    public static void main(String[] args) throws IOException {

        final HttpServer server = startServer();

        System.out.println("Server started at http://localhost:8080/api/v1");
        System.out.println("Press ENTER to stop...");

        System.in.read(); // keep running

        server.shutdownNow();
    }
}
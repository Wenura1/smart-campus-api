package com.smartcampus;

import com.smartcampus.config.AppConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;

// main class to start server
public class Main {

    // FULL base path here (IMPORTANT)
    public static final String BASE_URI = "http://localhost:8080/api/v1/";

    public static HttpServer startServer() {

        // use AppConfig
        final AppConfig config = new AppConfig();

        return GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI),
                config
        );
    }

    public static void main(String[] args) throws IOException {

        HttpServer server = startServer();

        System.out.println("Server running:");
        System.out.println(" http://localhost:8080/api/v1/info");
        System.out.println(" http://localhost:8080/api/v1/rooms");

        System.in.read();
        server.shutdownNow();
    }
}
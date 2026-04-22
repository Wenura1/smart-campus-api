package com.smartcampus;

import com.smartcampus.config.AppConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;

public class Main {

    // base server URL ONLY (no /api/v1 here)
    public static final String BASE_URI = "http://localhost:8080/";

    public static HttpServer startServer() {

        // use AppConfig (IMPORTANT)
        final AppConfig config = new AppConfig();

        return GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI),
                config
        );
    }

    public static void main(String[] args) throws IOException {

        final HttpServer server = startServer();

        // simple output
        System.out.println("Server running:");
        System.out.println(" http://localhost:8080/api/v1/info");
        System.out.println(" http://localhost:8080/api/v1/rooms");

        System.in.read();
        server.shutdownNow();
    }
}
package com.smartcampus.config;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

// base path for ALL endpoints
@ApplicationPath("/api/v1")
public class AppConfig extends ResourceConfig {

    public AppConfig() {
        // scan resource classes
        packages("com.smartcampus.resources");
    }
}
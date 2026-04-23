package com.smartcampus.config;

import org.glassfish.jersey.server.ResourceConfig;

// config class to register resources
public class AppConfig extends ResourceConfig {

    public AppConfig() {
        // scan ALL project packages
        packages("com.smartcampus");
    }
}
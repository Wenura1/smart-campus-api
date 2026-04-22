package com.smartcampus.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// this sets the base URL for all endpoints
// everything will be under /api/v1
@ApplicationPath("/api/v1")
public class AppConfig extends Application {
    // no need to write anything inside
}
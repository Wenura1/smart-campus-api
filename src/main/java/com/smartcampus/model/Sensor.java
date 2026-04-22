package com.smartcampus.model;

// model for sensor
public class Sensor {

    private int id;            // unique id
    private String type;       // sensor type (e.g., CO2)
    private String status;     // ACTIVE / MAINTENANCE / OFFLINE
    private double currentValue; // latest reading
    private int roomId;        // linked room

    public Sensor() {}

    public Sensor(int id, String type, String status, double currentValue, int roomId) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.currentValue = currentValue;
        this.roomId = roomId;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getCurrentValue() { return currentValue; }
    public void setCurrentValue(double currentValue) { this.currentValue = currentValue; }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
}
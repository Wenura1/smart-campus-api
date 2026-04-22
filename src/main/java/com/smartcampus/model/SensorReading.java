package com.smartcampus.model;

// model for sensor reading
public class SensorReading {

    private int id;          // reading id
    private long timestamp;  // time of reading
    private double value;    // measured value

    public SensorReading() {}

    public SensorReading(int id, long timestamp, double value) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
}
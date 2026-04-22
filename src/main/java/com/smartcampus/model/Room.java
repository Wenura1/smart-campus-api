package com.smartcampus.model;

// model class for Room
public class Room {

    private int id;
    private String name;
    private int capacity;

    // empty constructor (needed for JSON)
    public Room() {}

    // constructor to create room
    public Room(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    // getters and setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
}
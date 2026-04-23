# Smart Campus Room API

This project is a simple REST API for managing rooms, sensors, and sensor readings in a smart campus system.

---

## Features

- Get all rooms
- Get room by ID
- Add new room
- Update room
- Delete room

- Get all sensors
- Add sensor to a room
- Filter sensors by type

- Add sensor readings
- View sensor readings

- Input validation (name, capacity, sensor type)
- Basic error handling (400, 404, 403, 409)

---

## Base URL

http://localhost:8080/api/v1

---

## Endpoints

### Rooms
- GET /rooms
- GET /rooms/{id}
- POST /rooms
- PUT /rooms/{id}
- DELETE /rooms/{id}

### Sensors
- GET /sensors
- GET /sensors/{id}
- POST /sensors
- PUT /sensors/{id}
- DELETE /sensors/{id}

Filter:
- GET /sensors?type=temperature

### Sensor Readings
- GET /sensors/{id}/readings
- POST /sensors/{id}/readings

---

## Example Request

### Add Room
{
"name": "New Room",
"capacity": 25
}

### Add Sensor
{
"type": "temperature",
"status": "ACTIVE",
"roomId": 1
}

### Add Reading
{
"value": 25.5
}

---

## Error Handling

- 400 Bad Request → invalid input
- 404 Not Found → resource not found
- 409 Conflict → room has sensors assigned
- 403 Forbidden → sensor under maintenance

---

## API Testing Evidence

Screenshots of API testing using Postman are available in the `/screenshots` folder.

They include:
- Successful requests (200, 201)
- Validation errors (400)
- Not found errors (404)
- Business rule violations (409)

## Notes

- ID is auto generated
- Data is stored in memory (no database)
- Sensor must belong to an existing room
- Readings update the sensor's current value  
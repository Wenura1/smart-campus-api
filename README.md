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
- Advanced error handling (400, 404, 403, 409, 422, 500)

---

## Base URL

http://localhost:8080/api/v1

---

## Build and Run Instructions

Clone the repository:

git clone https://github.com/your-username/smart-campus-api.git

Open the project in IntelliJ IDEA
Build using Maven:

mvn clean install

Deploy on a servlet container (e.g., Tomcat)
Start the server
Access API at:

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

## Sample curl Commands

- Create Room:
curl -X POST http://localhost:8080/api/v1/rooms -H "Content-Type: application/json" -d '{"name":"Lab A","capacity":40}'

- Get Rooms:
curl http://localhost:8080/api/v1/rooms

- Create Sensor:
curl -X POST http://localhost:8080/api/v1/sensors -H "Content-Type: application/json" -d '{"type":"temperature","roomId":1}'

- Filter Sensors:
curl http://localhost:8080/api/v1/sensors?type=temperature

- Add Reading:
curl -X POST http://localhost:8080/api/v1/sensors/1/readings -H "Content-Type: application/json" -d '{"value":25}'

## Error Handling

- 400 Bad Request → Invalid input data
- 404 Not Found → Resource does not exist
- 409 Conflict → Room contains sensors
- 422 Unprocessable Entity → Invalid linked resource
- 403 Forbidden → Sensor unavailable (maintenance)
- 500 Internal Server Error → Unexpected failure

---

## API Testing Evidence

Screenshots of API testing using Postman are available in the `/screenshots` folder.

They include:
- Successful requests (200, 201)
- Validation errors (400)
- Not found errors (404)
- Business rule violations (409, 422, 403)
- Server error handling (500)

## Notes

- ID is auto generated
- Data is stored in memory (no database)
- Sensor must belong to an existing room
- Readings update the sensor's current value  

---------------------------------------------------------------------------------------------------

# Coursework Report Answers

## Part 1: Service Architecture & Setup
### Question 1: JAX-RS Resource Lifecycle

By default, JAX-RS will generate a new instance of the resource class for each HTTP request. That means each HTTP request will have its own unique instance of the resource class, making it easier to avoid interference between different requests, as they do not have shared states.

However, in the current implementation, there is shared state data among requests, such as the static HashMaps that are used to store information about rooms and sensors. This means that requests can alter the shared state data simultaneously, creating the risk of concurrent modification.

---
### Question 2: HATEOAS

HATEOAS (Hypermedia as the Engine of Application State) allows clients to navigate an API dynamically through links provided in responses. In this project, the discovery endpoint provides links to key resources such as rooms and sensors.

This approach improves flexibility because clients do not need to hardcode endpoint URLs. Instead, they can discover available operations through API responses, making the system more maintainable and adaptable to changes.

---

## Part 2: Room Management
### Question 1: Returning IDs vs Full Objects

Returning only IDs reduces the amount of data transferred over the network. However, it requires additional API calls to retrieve full details.

In this implementation, full objects are returned to simplify client interaction, even though it slightly increases response size.

---
### Question 2: DELETE Idempotency

The idempotent nature of DELETE means that making the same request multiple times will have no effect on the final state of the resource (e.g. if a room has been deleted once successfully, any further requests to delete that same room will return a 404 Not Found).

Even though each request will generate a different response after the first successful delete, the resource's state will remain at the same level as it did prior to any DELETE attempt, thereby meeting the definition of idempotency.

---

## Part 3: Sensor Operations & Linking
### Question 1: Media Type Handling

The API uses @Consumes(MediaType.APPLICATION_JSON) to ensure that it only accepts input that is in the form of JSON. In case of an alternate format, the system automatically returns a 415 Unsupported Media Type response.

---
### Question 2: QueryParam vs PathParam

Query parameters are responsible for filtering of resources such as sensors by type through /sensors?type=temperature. This approach is quite flexible and allows optional filters without changing the endpoint architecture.

Path parameters used to filter (e.g., /sensors/type/temperature) reduce flexibility and make it more difficult to extend the system in the future. Therefore, query parameters would be more appropriate for searching and filtering purposes.

---

## Part 4: Sub-Resources
### Question 1: Sub-Resource Locator Pattern

The delegation of nested endpoints is carried out with the sub-resource locator pattern, which delegates nested endpoints such as /sensors/{id}/readings to another resource type. This enhances maintainability and organization of codes.

---
### Question 2: Historical Data Management

The sensor readings are stored as a collection that is linked to the sensor. On adding a new reading, it is appended to the list and the currentValue field of the sensor is updated.

This makes the sensor consistent with the current sensor reading and its current condition.

---

## Part 5: Error Handling, Exception Mapping & Logging
### Question 1: 409 Conflict

A 409 Conflict response is returned when attempting to delete a room that still has sensors assigned. This prevents invalid system states.

---
### Question 2: 422 Unprocessable Entity

A 422 response is used when a request is syntactically correct but semantically invalid, such as creating a sensor with a non-existent roomId.

---
### Question 3: 403 Forbidden

A 403 response is returned when attempting to add readings to a sensor that is in a MAINTENANCE state, indicating that the action is not allowed.

---
### Question 4: 500 Internal Server Error

A global exception handler is used to catch unexpected errors and return a 500 response. This prevents internal implementation details from being exposed.

---
### Question 5: Logging Filters

Logging filters are used to record information about requests and responses, such as HTTP method, URI, and response status. This method centralises logging and prevents resource methods from logging the same thing twice. 

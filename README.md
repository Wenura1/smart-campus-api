# Smart Campus Room API

This project is a simple REST API for managing rooms in a smart campus system.

## Features

* Get all rooms
* Get room by ID
* Add new room
* Update room
* Delete room
* Input validation (name and capacity)

## Base URL

http://localhost:8080/api/v1

## Endpoints

Get API Info

GET /api/v1/info

Get All Rooms

GET /api/v1/rooms

Add Room

POST /api/v1/rooms

Example:
{
"name": "New Room",
"capacity": 25
}

Update Room

PUT /api/v1/rooms/{id}

Example:
PUT /api/v1/rooms/1

{
"name": "Updated Lab",
"capacity": 50
}

Delete Room

DELETE /api/v1/rooms/{id}

Error Handling
Returns 404 if room ID not found
Returns 400 if request data is invalid
Testing

Tested using Postman for all endpoints.

## Notes

* ID is auto generated
* Validation prevents empty name and invalid capacity
* Data is stored in memory (no database)

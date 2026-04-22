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

GET /rooms
GET /rooms/{id}
POST /rooms
PUT /rooms/{id}
DELETE /rooms/{id}

## Example Request (POST)

{
"name": "New Room",
"capacity": 25
}

## Example Response

{
"status": "success",
"data": {
"id": 3,
"name": "New Room",
"capacity": 25
}
}

## Notes

* ID is auto generated
* Validation prevents empty name and invalid capacity
* Data is stored in memory (no database)

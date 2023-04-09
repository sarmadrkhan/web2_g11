# Web Applications 2

## Ticketing Service Backend

This is a backend service for a ticketing system developed using Kotlin and Spring Boot.

## Group 11

- s294560 - Khan, Sarmad Raees
- s301120 - Zhao, Longsheng
- s297984 - Wang, Junbaio
- s298885 - Khan, Muhammad Sarib

## Labs and Folder Structure

- Lab 0 - Lab on Postgres Containers With Persistent Data
- Lab 1 - Kotlin Exercises
- Lab 2 - Setting Up A Baisc Service
  - Client - React based front end
  - Server - Spring Boot based back end

## API Endpoints

Currently, following endpoints are implemented:

### GET `/API/products/`

- fetch all products

### GET `/API/products/{productID}`

- fetch the details of a product
- fail if it doesn't exist

### GET `/API/profiles/{email}`

- fetch the details of a user profile
- fail if the email doesn't exist

### POST `/API/profiles/`

- create a new entry in the profiles table
- fail if the email already exists

### PUT `/API/profiles/{email}`

- update the details of a user profile given its id
- fail if the email doesn't exist

## Technology Stack

The ticketing service backend will be developed using the following technologies:

- Kotlin: A modern programming language that runs on the Java Virtual Machine (JVM).
- Spring Boot: A popular framework for building web applications in Java and Kotlin.
- Docker: A containerization platform that allows applications to run consistently across different environments.
- Kubernetes: An open-source container orchestration platform that automates the deployment, scaling, and management of containerized applications.
- PostgreSQL: An open-source relational database management system that will be containerized using Docker and used as the backend database for the ticketing system.
- The client-side of the ticketing system will be developed using React, a popular JavaScript library for building user interfaces.
- The user interface will interact with the backend using RESTful API endpoints provided by the Spring Boot application.

## Deployment

// _todo_

## Conclusion

// _todo_

# Web Applications 2

## Ticketing Service Backend

This is a backend service for a ticketing system developed using Kotlin and Spring Boot. The application will be containerized using Docker and will be deployed on Kubernetes for scalability and availability.

## Group 11

- s294560 - Khan, Sarmad Raees
- s301120 - Zhao, Longsheng
- s297984 - Wang, Junbaio
- s298885 - Khan, Muhammad Sarib

## Features

The ticketing service backend will provide the following features:
// _todo_

## Technology Stack

The ticketing service backend will be developed using the following technologies:

- Kotlin: A modern programming language that runs on the Java Virtual Machine (JVM).
- Spring Boot: A popular framework for building web applications in Java and Kotlin.
- Docker: A containerization platform that allows applications to run consistently across different environments.
- Kubernetes: An open-source container orchestration platform that automates the deployment, scaling, and management of containerized applications.
- PostgreSQL: An open-source relational database management system that will be containerized using Docker and used as the backend database for the ticketing system.
- The client-side of the ticketing system will be developed using React, a popular JavaScript library for building user interfaces. The user interface will interact with the backend using - RESTful API endpoints provided by the Spring Boot application.

## Deployment

The ticketing system will be containerized using Docker and deployed on Kubernetes for scalability and availability. The Docker image of the Spring Boot application will be pushed to a container registry, and Kubernetes will pull the image to deploy it to the cluster. Kubernetes will manage the deployment, scaling, and availability of the ticketing system. The PostgreSQL database will also be containerized using Docker and deployed alongside the Spring Boot application on Kubernetes.

## Conclusion

In conclusion, the ticketing service backend will provide a robust and scalable solution for managing tickets and user accounts. The use of Kubernetes and Docker will ensure that the application is highly available and can be easily scaled to meet increasing demand. The PostgreSQL database will provide a reliable and efficient storage solution for the ticketing system.

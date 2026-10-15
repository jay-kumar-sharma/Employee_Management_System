# Employee Management System

### A Java-Based Microservices Application

A backend application designed to manage employee information, attendance,
and payroll operations using Java, Spring Boot, and a microservices architecture.

The project demonstrates service separation, REST API development,
service discovery, centralized API routing, and authentication.

---

## Overview

The Employee Management System organizes employee-related functionality
into independent services.

Each service is responsible for a specific business capability, making
the application easier to maintain, develop, and extend.

## Features

- Employee management
- Attendance management
- Payroll management
- Authentication using JWT
- Role-based authorization
- RESTful API development
- Microservices-based architecture
- Service discovery using Netflix Eureka
- API routing through Spring Cloud Gateway

> Feature availability depends on the current implementation and configuration.

## Technology Stack

| Category | Technology |
|---|---|
| Programming Language | Java 17 |
| Backend Framework | Spring Boot |
| Security | Spring Security |
| Authentication | JWT |
| Microservices | Spring Cloud |
| Service Discovery | Netflix Eureka |
| API Gateway | Spring Cloud Gateway |
| ORM | JPA / Hibernate |
| Database | MySQL |
| Build Tool | Maven |
| Version Control | Git and GitHub |

## Architecture

The project contains the following services:

| Service | Responsibility |
|---|---|
| Auth Service | Authentication-related functionality |
| Employee Service | Employee-related operations |
| Attendance Service | Attendance-related operations |
| Payroll Service | Payroll-related operations |
| Discovery Server | Service registration and discovery |
| API Gateway | Entry point for routing API requests |

### Request Flow

```text
Client Application
        |
        v
   API Gateway
        |
        v
  Service Discovery
        |
        +------------------+
        |        |         |
        v        v         v
      Auth   Employee  Attendance
                         Service

        Payroll Service
```

The diagram illustrates the main components conceptually.
Actual routing and service-to-service communication depend on
the application's configuration.

## Project Structure

```text
Employee_Management_System/
├── api-gateway/
├── attendance-service/
├── auth-service/
├── discovery-server/
├── employee-service/
├── payroll-service/
├── .gitignore
└── README.md
```

## Prerequisites

Install the following software before running the project:

- JDK 17
- Maven
- MySQL
- Git
- An IDE such as IntelliJ IDEA or Eclipse

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/jay-kumar-sharma/Employee_Management_System.git
```

Navigate to the project directory:

```bash
cd Employee_Management_System
```

### 2. Configure the database

Create the required MySQL database or databases according to each
service's configuration.

Update the database connection properties in the relevant
`application.properties` or `application.yml` files.

Configure your database credentials locally. Do not commit real
passwords, tokens, or other secrets to GitHub.

### 3. Configure service ports

Review each service's configuration and ensure that the ports are
compatible with the API Gateway and service discovery settings.

### 4. Build the services

Each service has its own Maven project.

Open a terminal inside a service directory containing its `pom.xml`
and run:

```bash
mvn clean install
```

Repeat for the other services as required.

### 5. Start the services

Start the services in the order required by your configuration:

1. Discovery Server
2. Auth Service
3. Employee Service
4. Attendance Service
5. Payroll Service
6. API Gateway

Use the actual application ports and configuration defined in each service.

For an individual service, run:

```bash
mvn spring-boot:run
```

**Note:** Successful startup requires valid database settings,
compatible dependency versions, and correct service configuration.

## API Documentation

API endpoints can be tested using Postman or another REST client.

If Swagger/OpenAPI is configured for a service, use that service's
configured API documentation URL.

Add the verified Swagger URLs here once they are available.

## Testing

Automated tests should be run from the relevant service directories.

For Maven-based services:

```bash
mvn test
```

## Future Improvements

Potential improvements include:

- Automated integration testing
- Docker-based local development
- CI/CD using GitHub Actions
- Centralized logging and monitoring
- API documentation across services
- Improved configuration and secret management

These items represent possible enhancements, not necessarily
implemented features.

## Author

**Jay Kumar Sharma**

- GitHub: [jay-kumar-sharma](https://github.com/jay-kumar-sharma)

---

If you find this project useful, feel free to explore the repository.

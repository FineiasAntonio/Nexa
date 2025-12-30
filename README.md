# Nexa - Service Order Management System

![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)
![Version](https://img.shields.io/badge/version-0.0.1--SNAPSHOT-blue)
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.0-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-blue)

Nexa is a comprehensive Service Order Management System built with Java and Spring Boot, following the Hexagonal Architecture (Ports and Adapters) pattern to ensure a clean separation between domain logic and infrastructure.

## 🚀 Technologies

- **Java 17**
- **Spring Boot 3** (Web, Data JPA, Validation, Security, AMQP)
- **PostgreSQL** (Database)
- **RabbitMQ** (Message Broker)
- **Flyway** (Database Migrations)
- **Docker & Docker Compose** (Containerization)
- **OpenAPI / Swagger** (Documentation)

## 🏗 Architecture

The project follows **Hexagonal Architecture**:

- **Domain**: Contains the core business logic, entities, and ports (interfaces). It has no dependencies on external frameworks.
- **Configuration**: Contains the configuration of the application.
- **Infrastructure**: Contains the implementation of ports (Adapters), repositories, and framework-specific configurations.
    - **Application**: Controllers and DTOs.
    - **Service**: Implementations of business operations.
    - **Repository**: Database access.

## 🛠 Setup & Installation

### Prerequisites
- Java 17+
- Maven
- Docker & Docker Compose

### Running Infrastructure
Start the required services (PostgreSQL, RabbitMQ) using Docker Compose:

```bash
docker-compose -f docker-compose.dev.yaml up -d
```

- **PostgreSQL**: Port 5432
- **RabbitMQ**: Port 5672 (Management UI: [http://localhost:15672](http://localhost:15672) - User/Pass: `admin`/`admin`)

### Running the Application

```bash
mvn spring-boot:run
```

The application will start on port `8080` (context path `/api`).

## 📚 API Documentation

Once the application is running, you can access the Swagger UI documentation at:

[http://localhost:8080/api/swagger-ui.html](http://localhost:8080/api/swagger-ui.html)

## ⚡ Key Features

- **Service Orders**: Create and view service orders.
- **Customers**: Manage customer information.
- **Items**: Manage product catalog / inventory items.
- **Messaging**: Asynchronous event publishing (RabbitMQ) when Service Orders are created (Notification system).

## 🧪 Testing

Run the tests using Maven:

```bash
mvn test
```

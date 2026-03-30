# Library Management System - Microservices

A microservices-based Library Management System built with Spring Boot and Spring Cloud Gateway.

## Services

| Service | Port | Description |
|---|---|---|
| API Gateway | 8080 | Single entry point for all services |
| Member Service | 8081 | Manages library members and registrations |
| Book Service | 8082 | Manages book inventory and catalog |
| Borrow Service | 8083 | Handles book borrowing and returns |
| Reservation Service | 8084 | Manages book reservations |
| Notification Service | 8085 | Sends notifications to members |

## Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Cloud Gateway
- Spring Data JPA
- H2 / MySQL Database
- Springdoc OpenAPI (Swagger UI)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- MySQL (for Notification Service only)

### Run the Services

1. Clone the repository
   
git clone https://github.com/DhanukaPrabhashwara/Library-management-system.git


2. Open the project in IntelliJ IDEA

3. Start each service by running its main application class

4. Start the API Gateway last

### Accessing the APIs

**Direct Swagger UI:**
- Member Service: http://localhost:8081/swagger-ui.html
- Book Service: http://localhost:8082/swagger-ui.html
- Borrow Service: http://localhost:8083/swagger-ui.html
- Reservation Service: http://localhost:8084/swagger-ui.html
- Notification Service: http://localhost:8085/swagger-ui.html

**Via API Gateway (port 8080):**
- Members: http://localhost:8080/api/members
- Books: http://localhost:8080/api/books
- Borrows: http://localhost:8080/api/borrows
- Reservations: http://localhost:8080/api/reservations
- Notifications: http://localhost:8080/api/notifications

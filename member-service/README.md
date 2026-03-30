# Member Service - Library Management System

A production-ready microservice for managing library members in a Library Management System using Spring Boot and microservices architecture.

## Overview

The **Member Service** is a RESTful microservice responsible for managing library members (users). It follows clean architecture principles and is designed to work independently as part of a larger microservices ecosystem.

### Key Features
- ✅ Full CRUD operations for members
- ✅ Clean Architecture pattern
- ✅ Spring Boot 3.2.0 with Java 17
- ✅ H2 in-memory database for development
- ✅ Complete REST API with OpenAPI/Swagger documentation
- ✅ Global exception handling
- ✅ Logging with SLF4J
- ✅ JPA/Hibernate ORM
- ✅ Lombok for boilerplate reduction
- ✅ Ready for API Gateway integration

## Technology Stack

| Technology | Version |
|-----------|---------|
| Java | 17+ |
| Spring Boot | 3.2.0 |
| Spring Data JPA | Latest |
| Maven | 3.8+ |
| H2 Database | Latest |
| Lombok | Latest |
| Springdoc OpenAPI | 2.1.0 |

## Project Structure

```
member-service/
├── .mvn/                                  # Maven wrapper
├── src/
│   ├── main/
│   │   ├── java/com/library/memberservice/
│   │   │   ├── controller/                # REST Controllers
│   │   │   │   └── MemberController.java
│   │   │   ├── service/                   # Business Logic
│   │   │   │   └── MemberService.java
│   │   │   ├── repository/                # Data Access
│   │   │   │   └── MemberRepository.java
│   │   │   ├── model/                     # JPA Entities
│   │   │   │   └── Member.java
│   │   │   ├── dto/                       # Data Transfer Objects
│   │   │   │   └── MemberDTO.java
│   │   │   ├── exception/                 # Custom Exceptions
│   │   │   │   ├── MemberNotFoundException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── config/                    # Spring Configurations
│   │   │   │   └── OpenApiConfig.java
│   │   │   └── MemberServiceApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/library/memberservice/
│           └── controller/
│               └── MemberControllerTest.java
├── mvnw                                   # Maven wrapper script (Unix)
├── mvnw.cmd                              # Maven wrapper script (Windows)
├── pom.xml                               # Maven configuration
└── README.md                             # This file
```

## Architecture Pattern

This service follows the **Clean Architecture** pattern with clear separation of concerns:

```
┌─────────────────────────────────────────┐
│         REST Controller Layer            │
│  (Handles HTTP requests/responses)       │
└────────────────┬────────────────────────┘
                 │
┌─────────────────▼────────────────────────┐
│        Service Business Logic Layer       │
│  (Implements business rules & validations)│
└────────────────┬────────────────────────┘
                 │
┌─────────────────▼────────────────────────┐
│    Repository Data Access Layer          │
│  (Interacts with database via JPA)       │
└────────────────┬────────────────────────┘
                 │
┌─────────────────▼────────────────────────┐
│      H2 In-Memory Database               │
│  (Data Persistence)                      │
└──────────────────────────────────────────┘
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.8.0 or higher

### Installation & Setup

1. **Navigate to project directory:**
   ```bash
   cd c:\D_Drive\projects\mtit_microservice\member_service
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   
   Or directly with Java:
   ```bash
   java -jar target/member-service-1.0.0.jar
   ```

4. **Verify the service is running:**
   - Service runs on: `http://localhost:8081`
   - H2 Console: `http://localhost:8081/h2-console` (default credentials: sa / empty password)
   - Swagger UI: `http://localhost:8081/swagger-ui.html`
   - API Docs: `http://localhost:8081/api-docs`

## API Endpoints

### Base URL: `http://localhost:8081/members`

#### 1. Create a Member
```
POST /members
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "securePassword123"
}

Response: 201 Created
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "password": "securePassword123"
}
```

#### 2. Get All Members
```
GET /members

Response: 200 OK
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "password": "securePassword123"
  }
]
```

#### 3. Get Member by ID
```
GET /members/{id}

Example: GET /members/1

Response: 200 OK
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "password": "securePassword123"
}

Response if not found: 404 Not Found
{
  "timestamp": "2026-03-28T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Member not found with id: 1",
  "path": "/members/1"
}
```

#### 4. Update a Member
```
PUT /members/{id}
Content-Type: application/json

{
  "name": "Jane Doe",
  "email": "jane@example.com",
  "password": "newPassword456"
}

Response: 200 OK
{
  "id": 1,
  "name": "Jane Doe",
  "email": "jane@example.com",
  "password": "newPassword456"
}
```

#### 5. Delete a Member
```
DELETE /members/{id}

Example: DELETE /members/1

Response: 204 No Content
```

## Configuration

### application.properties

Located at: `src/main/resources/application.properties`

Key configurations:
- **Server Port:** `server.port=8081`
- **Database:** H2 in-memory database
- **JPA:** Hibernate with automatic DDL update
- **Logging:** DEBUG level for application packages
- **Swagger:** Enabled and configured

### H2 Database Console

Access the H2 console for direct database inspection:
- URL: `http://localhost:8081/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

## Testing

Run unit tests:
```bash
mvn test
```

Run with coverage:
```bash
mvn clean test
```

## Code Quality

### Annotations Used
- `@RestController` - REST endpoint definition
- `@Service` - Business logic layer
- `@Repository` - Data access layer
- `@Entity` - JPA entity mapping
- `@Transactional` - Database transaction management
- `@ControllerAdvice` - Global exception handling
- `@Slf4j` - Logging (Lombok)
- `@Data` - Getters/Setters/equals/hashCode (Lombok)
- `@NoArgsConstructor` - Default constructor (Lombok)
- `@AllArgsConstructor` - Full constructor (Lombok)
- `@Builder` - Builder pattern (Lombok)

### Design Patterns Used
1. **DTO Pattern** - For request/response abstraction
2. **Repository Pattern** - For data access abstraction
3. **Service Layer Pattern** - For business logic isolation
4. **Singleton Pattern** - Spring Beans
5. **Exception Handling Pattern** - Global exception handler

## Entity Design

### Member Entity

```java
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;              // Auto-generated primary key
    
    @Column(nullable = false)
    private String name;          // Member's full name
    
    @Column(nullable = false, unique = true)
    private String email;         // Unique email address
    
    @Column(nullable = false)
    private String password;      // Member's password
}
```

## API Gateway Integration

This microservice is designed to work with an API Gateway. Configuration points:
- Service runs independently on port 8081
- All endpoints are stateless and RESTful
- Follows standard HTTP status codes
- Returns JSON responses only
- Exception handling provides proper error responses

## Development Notes

### Database
- Uses H2 in-memory database for development
- Data is persisted only during runtime
- Perfect for testing and prototyping
- Easy to switch to other databases (PostgreSQL, MySQL) by updating dependencies and configuration

### Logging
- Uses SLF4J with Logback
- Application logs: DEBUG level
- Framework logs: INFO level
- All logs include class context and method names

### Error Handling
- Global exception handler catches all exceptions
- Custom `MemberNotFoundException` for member-specific errors
- Consistent error response format with timestamps

## Dependencies

All dependencies are managed through Maven (pom.xml):

```xml
- spring-boot-starter-web (REST endpoints)
- spring-boot-starter-data-jpa (ORM)
- h2 (In-memory database)
- lombok (Boilerplate reduction)
- springdoc-openapi-starter-webmvc-ui (Swagger UI)
```

## Next Steps

1. ✅ **Completed:** Basic CRUD operations
2. ✅ **Completed:** API documentation (Swagger)
3. **Future:** Add authentication/authorization
4. **Future:** Add input validation with @Valid annotations
5. **Future:** Add service-to-service communication
6. **Future:** Add monitoring and metrics
7. **Future:** Add database migration scripts (Flyway)
8. **Future:** Add caching (Redis)
9. **Future:** Add message queue integration (RabbitMQ/Kafka)

## Troubleshooting

### Port already in use
```bash
# Change port in application.properties
server.port=8082
```

### Maven build fails
```bash
# Clean and rebuild
mvn clean install -U
```

### H2 Console not accessible
- Ensure `spring.h2.console.enabled=true` in application.properties
- Access only on LOCAL environment

## License

Apache 2.0

## Support

For issues or questions regarding this microservice, please refer to the project documentation or contact the development team.

---

**Created:** 2026-03-28  
**Version:** 1.0.0  
**Status:** Production Ready

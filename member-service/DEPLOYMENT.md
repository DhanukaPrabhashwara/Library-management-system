# Deployment Guide for Member Service

## Quick Start

### Build
```bash
mvn clean install
```

### Run
```bash
mvn spring-boot:run
```

## Endpoints Testing

### Using curl

#### 1. Create Member
```bash
curl -X POST http://localhost:8081/members \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123"
  }'
```

#### 2. Get All Members
```bash
curl -X GET http://localhost:8081/members \
  -H "Content-Type: application/json"
```

#### 3. Get Member by ID
```bash
curl -X GET http://localhost:8081/members/1 \
  -H "Content-Type: application/json"
```

#### 4. Update Member
```bash
curl -X PUT http://localhost:8081/members/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Doe",
    "email": "jane@example.com",
    "password": "newPassword123"
  }'
```

#### 5. Delete Member
```bash
curl -X DELETE http://localhost:8081/members/1 \
  -H "Content-Type: application/json"
```

## Access Points

| Resource | URL |
|----------|-----|
| Swagger UI | http://localhost:8081/swagger-ui.html |
| API Documentation | http://localhost:8081/api-docs |
| H2 Console | http://localhost:8081/h2-console |
| Members API | http://localhost:8081/members |

## H2 Database Configuration

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** (empty)

## Logs

Application logs are saved with DEBUG level for debugging purposes. Check console output or configure logging file location in `application.properties`:

```properties
logging.file.name=logs/member-service.log
```

## Deployment Options

### Option 1: Standalone JAR
```bash
mvn clean install
java -jar target/member-service-1.0.0.jar
```

### Option 2: Kubernetes
```bash
kubectl create deployment member-service --image=member-service:1.0.0
kubectl expose deployment member-service --type=LoadBalancer --port=8081
```

## Environment Variables

Set these optional environment variables:

```bash
export SERVER_PORT=8081
export SPRING_DATASOURCE_URL=jdbc:h2:mem:testdb
```

## Performance Notes

- H2 in-memory database: Fast for development/testing
- For production, switch to PostgreSQL/MySQL
- Single-threaded application by default
- Thread pool can be configured via Spring properties

## Security Notes

⚠️ **Important for Production:**
- Passwords are currently stored in plain text - implement password hashing (BCrypt)
- Enable Spring Security for authentication/authorization
- Use HTTPS for API communication
- Implement rate limiting
- Add API key authentication for service-to-service calls
- Remove H2 Console from production

## Monitoring

Add monitoring for:
- API response times
- Database query performance
- Error rates
- Memory usage

## Backup & Restore

Since H2 is in-memory, data persists only during runtime. For persistence, switch to a disk-based database.

## Troubleshooting

### Port 8081 already in use
```bash
# Find process using port 8081
netstat -ano | findstr :8081

# Or change port in application.properties
server.port=8082
```

### Maven build hangs
```bash
# Increase memory
mvn -Xmx1024m clean install
```

### Database connection errors
- Verify H2 driver is in classpath
- Check application.properties database URL

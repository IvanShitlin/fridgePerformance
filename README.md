# Fridge Performance Project Documentation

## Project Overview

The Fridge Performance project is a Spring Boot application designed to monitor and analyze refrigeration unit
performance. It integrates with a Higeco API system to collect and process performance metrics from refrigeration units.

## Technology Stack

- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- Spring Cloud OpenFeign
- PostgreSQL Database
- Lombok
- Gradle (with Kotlin DSL)

## Architecture and Design

The application computes refrigerator efficiency over a specified time interval. To do this, it invokes three distinct endpoints to:
1.	Retrieve the list of refrigerators registered in the system
2.	Fetch temperature setpoint data
3.	Obtain actual temperature readings for the given period

Before processing, the input time interval is validated for correctness.
In the current implementation, temperature setpoints are persisted in the database for each refrigerator. It is also prepared to store raw temperature data: to support this, a `CachedClientWrapper` layer has been developed, encapsulating the caching and storage logic.

### Configuration

The application uses the following configuration properties:

- PostgreSQL database connection
- Higeco API integration (base URL, credentials)
- Server port: 8080

## Setup

Docker Compose is required to run this application. Launch all services with:
 ```shell
    docker compose up --build -d
```

## Using

After successful launch you can use the app via curl or Postman like in example below:

 ```shell
    curl "localhost:8080/performance?from=2025-05-01T12:00:00.000Z&to=2025-05-01T13:00:00.000Z"
```

## Potential Improvements

1. **Security Enhancements**:
    - Implement proper authentication and authorization
    - Move sensitive credentials to secure vault
    - Enable HTTPS

2. **Code Quality**:
    - Add comprehensive unit and integration tests
    - Implement logging framework
    - Add API documentation (Swagger/OpenAPI)

3. **Features**:
    - Implement caching/saving mechanism with proper refreshing for temperature values
    - Add dynamic customization for Bucket values
    - Add monitoring and alerting

4. **Performance**:
    - Implement connection pooling
    - Add database indexing
    - Implement pagination for large datasets
    - Use non-blocking frameworks for dealing with highloads

5. **DevOps**:
    - CI/CD pipeline setup
    - Monitoring and logging infrastructure
    - Tracing

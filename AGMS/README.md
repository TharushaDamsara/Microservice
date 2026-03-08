# Automated Greenhouse Management System (AGMS)

AGMS is a full-fledged microservices project built using Spring Boot and Spring Cloud to automate and manage greenhouse environments.

## Architecture

- **Eureka Server**: Service discovery (Port 8761)
- **Config Server**: Externalized configuration (Port 8888)
- **API Gateway**: Routing and JWT Security (Port 8080)
- **Zone Service**: Zone management & IoT device registration (Port 8081)
- **Sensor Service**: Scheduled telemetry fetching (Port 8082)
- **Automation Service**: Processing telemetry and action logging (Port 8083)
- **Crop Service**: Crop lifecycle tracking (Port 8084)

## Prerequisites

- Java 17+
- Maven 3.6+
- IoT API access (configured in `config-repo`)

## Startup Instructions

Run the services in the following order:

1. **Config Server**:
   ```bash
   cd AGMS/config-server
   mvn spring-boot:run
   ```
2. **Eureka Server**:
   ```bash
   cd AGMS/eureka-server
   mvn spring-boot:run
   ```
3. **API Gateway**:
   ```bash
   cd AGMS/api-gateway
   mvn spring-boot:run
   ```
4. **Domain Services** (Zone, Sensor, Automation, Crop):
   ```bash
   cd AGMS/[service-name]
   mvn spring-boot:run
   ```

## API Documentation

A Postman collection is included in the root directory: `AGMS/postman_collection.json`.

### JWT Security

Base routes are protected. You must include a `Bearer <token>` in the `Authorization` header. A default secret is configured in `api-gateway.yml`.

## Inter-Service Workflow

1. **Zone Creation**: User creates a zone; the system registers a device with the External IoT API.
2. **Telemetry Fetching**: Sensor Service fetches telemetry every 10s from the IoT API.
3. **Automation**: Telemetry is forwarded to the Automation Service. The service fetches thresholds from the Zone Service via OpenFeign and logs actions (FAN_ON/HEATER_ON).
4. **Crop Management**: Users track crop batches through their lifecycle.

## Eureka Dashboard

Once all services are running, visit `http://localhost:8761` to see the registration status.
![Eureka Dashboard](docs/eureka-dashboard.png)
*(Note: Placeholder for actual dashboard screenshot)*

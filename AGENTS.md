# Agent Instructions for GMUP

## Architecture
- **Hexagonal (Ports & Adapters):** Strict separation of concerns.
  - `core/`: Domain models and business logic (pure Java, no framework dependencies).
  - `port/`: Interfaces for inbound (use cases) and outbound (infrastructure) communication.
  - `adapter/`: Concrete implementations (Controllers, JPA Repositories, MinIO services).
- **Domain Purity:** Keep `core/` free from Spring/JPA annotations.

## Development & Build
- **Build Tool:** Maven (`mvn`).
- **Standard Commands:**
  - Build/Compile: `mvn clean install`
  - Run Application: `mvn spring-boot:run`
- **Codegen:** MapStruct is used for object mapping. If code completion fails or changes aren't reflected, perform a `mvn clean compile` to trigger annotation processing.

## Infrastructure Prerequisites
- **MinIO:** Required for file storage. Start using:
  ```bash
  docker-compose up -d
  ```
  - API: `http://localhost:9000`
  - Console: `http://localhost:9001`
  - Credentials: `admin` / `admin123` (see `docker-compose.yml`)

## Workflow Conventions
- **New Feature:** 
  1. Define the business logic in `core/service`.
  2. Define the interface in `port/`.
  3. Implement the adapter in `adapter/`.
  4. Inject the port in the appropriate `adapter/inbound` or `core/service` component.

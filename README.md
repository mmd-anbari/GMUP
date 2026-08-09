# GMUP (Generic Management & Upload Platform)

## Overview
GMUP is a robust, modular, and secure platform designed for efficient file and user management. Built with a focus on Clean Architecture (Hexagonal Architecture), the project separates core business logic from external frameworks, ensuring maintainability and scalability.

## Key Capabilities

### 1. User Management
* **Authentication:** Secure user registration and authentication flow.
* **Profile Management:** Intuitive tools to handle user profiles and security details.
* **Security:** Implements strong password encoding and security configurations to protect user data.

### 2. File Management System
* **Seamless Uploads:** Support for file uploads with comprehensive validation.
* **Metadata Tracking:** Every file is indexed with persistent metadata, allowing for easy retrieval and management.
* **Secure Downloads:** Implements a token-based system to generate presigned URLs, ensuring that file access is secure and controlled.
* **Storage Integration:** Built-in integration with MinIO for scalable, high-performance object storage.

### 3. Architecture & Design
* **Ports & Adapters (Hexagonal Architecture):** The system structure promotes independence from external frameworks (UI, Database, Storage), making it easier to evolve and test.
* **Clean Logic:** Core services and domain models are isolated, focusing on business requirements rather than infrastructure details.

## Technical Stack
* **Language:** Java
* **Framework:** Spring Boot
* **Persistence:** JPA / Hibernate
* **Storage:** MinIO
* **Frontend:** Thymeleaf (Auth, Dashboard, Index views)

## Development Status
⚠️ **Under Active Development**
This project is currently in the active development phase. Features, API structures, and documentation are evolving. Contributions and feedback are welcome as we work towards a stable release.

---
*Built with clean architecture principles.*

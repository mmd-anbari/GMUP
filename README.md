# 🚀 GMUP (Generic Management & Upload Platform)

> **⚠️ Development Status:** This project is under active development. API structures, database schemas, and core services may evolve over time.

A comprehensive, modular, and secure platform for user and file management. This system is meticulously designed around **Clean Architecture (Hexagonal Architecture / Ports and Adapters)** principles to ensure the core business logic remains completely independent of external frameworks, databases, and user interfaces, making it highly maintainable and scalable.

---

## ✨ Key Features

### 👤 User Management
* **Authentication & Registration:** Secure flows for user sign-up and login.
* **Robust Security:** Implements strong password encoding and strict security standards to protect sensitive user data.
* **Profile Management:** Structured tools for managing user information and access levels.

### 📁 File Management System
* **Seamless & Secure Uploads:** Supports file uploads with comprehensive validations.
* **MinIO Storage Integration:** Fully integrated with MinIO for distributed, scalable, and high-performance object storage.
* **Controlled Downloads:** Implements a token-based system to generate presigned URLs, ensuring file access is highly secure.
* **Metadata Tracking:** Persistent storage of file metadata in the database for rapid search and retrieval.

---

## 🏗 Architecture & Design

This project strictly adheres to **Clean Architecture** principles:
* **Core / Domain:** The heart of the system containing business entities and logic, with zero dependencies on outer layers.
* **Ports:** Interfaces defining the inbound and outbound communication boundaries between the core and the outside world.
* **Adapters:** Technical implementations for communicating with the database (JPA), cloud storage (MinIO), web controllers (Spring Web), and security mechanisms.

---

## 🛠 Tech Stack

* **Language:** Java
* **Framework:** Spring Boot
* **Persistence / ORM:** JPA / Hibernate (Compatible with PostgreSQL, MySQL, etc.)
* **Object Storage:** MinIO (S3-compatible)
* **Frontend (SSR):** Thymeleaf (For Auth, Dashboard, and Index views)

---

## 🚀 Getting Started

### Prerequisites
To run this project locally, you will need:
* **Java 17** (or higher)
* **Maven**
* A running **MinIO** server
* A relational database (e.g., PostgreSQL or MySQL)

### Installation & Execution
1. Clone the repository:
   ```bash
   git clone [https://github.com/mmd-anbari/gmup.git](https://github.com/mmd-anbari/gmup.git)

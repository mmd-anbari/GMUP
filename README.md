<br/>
<div align="center">
  <a href="https://github.com">
    <img src="https://cdn-icons-png.flaticon.com/512/3214/3214406.png" alt="Logo" width="100" height="100">
  </a>

  <h1 align="center">🌟 GMUP 🌟</h1>

  <p align="center">
    <strong>Enterprise-Grade File Management & Upload Service</strong>
    <br/>
    <i>Architected for scale, performance, and ultimate decoupling.</i>
    <br/>
    <br/>
    <a href="#-features"><strong>Explore the docs »</strong></a>
    <br/>
    <br/>
    <a href="#">View Demo</a>
    ·
    <a href="#">Report Bug</a>
    ·
    <a href="#">Request Feature</a>
  </p>
</div>

<div align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.2.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="Postgres" />
  <img src="https://img.shields.io/badge/MinIO-C7202C?style=for-the-badge&logo=minio&logoColor=white" alt="Minio" />
</div>

<br/>

## 📖 About The Project

**GMUP** is a meticulously crafted backend service designed to handle file streams, metadata, and user integrations safely and efficiently. By employing **Ports and Adapters (Hexagonal Architecture)**, the system's core domain remains completely independent of its delivery mechanisms (REST APIs) and persistence layers (JPA / MinIO). 

This means you get a system where the business rules dictate the infrastructure, not the other way around.

<br/>

## 💎 Features

<table align="center">
  <tr>
    <td width="50%">
      <h3>☁️ Object Storage Ready</h3>
      <p>Seamlessly streams large files directly into MinIO buckets without overwhelming application memory.</p>
    </td>
    <td width="50%">
      <h3>🧠 Smart Metadata</h3>
      <p>Persists file details (MIME type, original name, path) in PostgreSQL, creating a reliable index of all uploads.</p>
    </td>
  </tr>
  <tr>
    <td>
      <h3>🛡️ Built-in Validations</h3>
      <p>Automatically checks for file duplications before initiating the upload sequence to save bandwidth and storage.</p>
    </td>
    <td>
      <h3>🏗️ Hexagonal Core</h3>
      <p>Zero dependencies on Spring within the Domain layer. Clean abstraction using Inbound and Outbound ports.</p>
    </td>
  </tr>
</table>

<br/>

## 🗺️ Roadmap

- [ ] **Authentication Layer:** Add secure Sign In and Sign Up using JWT.
- [ ] **Personal Workspaces:** Isolate user storage logic so users only interact with their own files.
- [ ] **Rate Limiting:** Implement limits on upload frequencies to prevent abuse.
- [ ] **File Retrieval:** Implement secure download endpoints with temporary pre-signed URLs.

<br/>

## ⚙️ Getting Started

Follow these steps to get a local copy up and running.

### Prerequisites

Ensure your environment is equipped with:
* **Java 21**
* **PostgreSQL** (Port: `5432`)
* **MinIO** (Port: `9000`)

### Installation & Run

<details>
  <summary><b>Click to expand setup instructions</b></summary>
  
  <br/>
  
  1. **Clone the repo**
     ```sh
     git clone [https://github.com/your_username/gmup.git](https://github.com/your_username/gmup.git)
     ```
  2. **Configure your properties** (`src/main/resources/application.properties`)
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/gmup
     spring.datasource.username=postgres
     spring.datasource.password=55455
     
     minio.endpoint=http://localhost:9000
     minio.access-key=minioadmin
     minio.secret-key=minioadmin
     ```
  3. **Run the application**
     ```sh
     ./mvnw spring-boot:run
     ```
</details>

<br/>

## 📐 Architecture Deep Dive

```mermaid
graph TD;
    A[REST Controllers] -->|Inbound Port| B(Core: UploadFileService);
    B -->|Outbound Port| C[PostgreSQL Adapter];
    B -->|Outbound Port| D[MinIO Adapter];

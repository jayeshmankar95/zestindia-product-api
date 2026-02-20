# zestindia

# 🛒 Product REST API

A production-ready Spring Boot RESTful API for managing Products and Items with full CRUD operations, 
JWT authentication, role-based authorization, Swagger documentation, and Dockerized deployment.

---

## 🚀 Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA (Hibernate)**
- **Spring Security + JWT**
- **MySQL Database**
- **Swagger / OpenAPI**
- **Docker & Docker Compose**
- **Lombok**
- **Maven**

---

## Project Architecture

This project follows a clean layered architecture:

Controller Layer → Handles REST endpoints
Service Layer → Business logic
Repository Layer → Database interaction
DTO Layer → Request/Response mapping
Security Layer → JWT authentication & RBAC
Exception Layer → Global error handling

---

## 🔐 Security Features

- JWT Authentication
- Refresh Token support
- Role-Based Authorization (ADMIN / USER)
- Method-level security with `@PreAuthorize`
- Stateless session management

---

## 📦 Database Schema

### Product Table

| Column | Type |
|--------|------|
id | INT (PK)
product_name | VARCHAR
created_by | VARCHAR
created_on | TIMESTAMP
modified_by | VARCHAR
modified_on | TIMESTAMP

---

### Item Table

| Column | Type |
|--------|------|
id | INT (PK)
product_id | INT (FK)
quantity | INT

---

## 📖 API Documentation (Swagger)

Access Swagger UI:

http://localhost:8080/swagger-ui/index.html

Use the **Authorize 🔒 button** to enter JWT token.

---

## 🔑 Authentication Flow

### 1️⃣ Login

POST /auth/login

Request:

```json
{
  "username": "admin",
  "password": "admin123",
  "role": "ADMIN"
}
Response:
{
  "accessToken": "...",
  "refreshToken": "..."
}
________________________________________
2️⃣ Use Token
In Swagger or Postman header:
Authorization: Bearer <accessToken>
________________________________________
3️⃣ Refresh Token
POST /auth/refresh
Request:
{
  "refreshToken": "..."
}
________________________________________

📚 Main API Endpoints
Product APIs
Method	Endpoint	Role
GET	/api/v1/products	USER / ADMIN
GET	/api/v1/products/{id}	USER / ADMIN
POST	/api/v1/products	ADMIN
PUT	/api/v1/products/{id}	ADMIN
DELETE	/api/v1/products/{id}	ADMIN
GET	/api/v1/products/{id}/items	USER / ADMIN
________________________________________
📄 Standard API Response Format
Success Response
{
  "success": true,
  "message": "Products fetched successfully",
  "data": {}
}
________________________________________
Error Response
{
  "message": "Product not found",
  "status": 404,
  "timestamp": "2026-02-20T10:30:00"
}
________________________________________
⚙️ Running Locally
1️⃣ Clone Repo
git clone <repo-url>
cd zestindia
________________________________________
2️⃣ Configure Database
Update application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/productdb
spring.datasource.username=root
spring.datasource.password=root
________________________________________
3️⃣ Build & Run
mvn clean install
mvn spring-boot:run
________________________________________
🐳 Running with Docker (Recommended)
Step 1 — Build JAR
mvn clean package
________________________________________
Step 2 — Start Containers
docker-compose up --build
________________________________________
Step 3 — Access Application
Swagger:
http://localhost:8080/swagger-ui/index.html
________________________________________
🧪 Running Tests
mvn test
________________________________________
🧠 Key Features Implemented
•	Clean Architecture
•	DTO Mapping
•	Pagination Support
•	Global Exception Handling
•	JWT + Refresh Token Security
•	Role-Based Access Control
•	Swagger Documentation
•	Dockerized Deployment
________________________________________
👨‍💻 Author
Jayesh Mankar
Java Backend Developer
________________________________________
📌 Assignment Completion Status
All required features implemented successfully:
✔ REST API Design
✔ Security Implementation
✔ Pagination Support
✔ Standard Error Handling
✔ Swagger Documentation
✔ Docker Deployment
________________________________________



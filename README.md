# Hospital Management System

A RESTful Hospital Management System built using Spring Boot, Spring Security, Spring Data JPA, Hibernate, and MySQL.

## 🚀 Features

- Patient management
- Doctor management
- Department management
- Insurance management
- Appointment management
- CRUD REST APIs
- DTO-based request handling
- Request validation
- Global exception handling
- JWT-based authentication
- Role-based authorization
- BCrypt password encryption
- Pagination and search
- Entity relationships using JPA/Hibernate

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- JWT
- Maven
- Lombok
- Postman
- IntelliJ IDEA

## 🏗️ Project Architecture

```text
Client / Postman
       ↓
   Controller
       ↓
     DTO
       ↓
    Service
       ↓
  Repository
       ↓
     MySQL
```

## 📁 Project Structure

```text
src/main/java/com/abhirajput_19/youtube/hospitalManagement

├── config
├── Controller
├── dto
├── entity
├── exception
├── repository
├── security
└── service
```

## 🔐 Authentication & Authorization

The application uses JWT-based authentication with Spring Security.

Roles

* ADMIN
* DOCTOR
* PATIENT

Authentication flow:

```text
Login
  ↓
Username + Password
  ↓
AuthenticationManager
  ↓
JWT Token
  ↓
Bearer Token
  ↓
JWT Authentication Filter
  ↓
SecurityContext
  ↓
Role-based Authorization
```

Passwords are securely stored using BCrypt hashing.

## 🔗 Main API Endpoints

Authentication

```text
POST /api/auth/register
POST /api/auth/login
```

Patients

```text
GET    /api/patients
GET    /api/patients/{id}
POST   /api/patients
PUT    /api/patients/{id}
DELETE /api/patients/{id}
```

Doctors

```text
GET    /api/doctors
GET    /api/doctors/{id}
POST   /api/doctors
PUT    /api/doctors/{id}
DELETE /api/doctors/{id}
```

Departments

```text
GET    /api/departments
GET    /api/departments/{id}
POST   /api/departments
PUT    /api/departments/{id}
DELETE /api/departments/{id}
```

Insurance

```text
GET    /api/departments
GET    /api/departments/{id}
POST   /api/departments
PUT    /api/departments/{id}
DELETE /api/departments/{id}
```

Appointments

```text
GET    /api/appointments
GET    /api/appointments/{id}
POST   /api/appointments
PUT    /api/appointments/{id}
DELETE /api/appointments/{id}
```

## 🔄 Entity Relationships

The project demonstrates different JPA relationships:

```text
Patient ───── One-to-One ───── Insurance

Patient ───── One-to-Many ──── Appointment

Doctor ────── One-to-Many ──── Appointment

Department ── Many-to-Many ─── Doctor

Appointment ─ Many-to-One ──── Patient

Appointment ─ Many-to-One ──── Doctor
```

## ✅ Validation & Exception Handling

The application uses Jakarta Bean Validation for validating incoming requests.

Examples:

```text
@NotBlank
@NotNull
@Email
@Past
@Future
```

A global exception handler provides consistent error responses for:

* Validation errors
* Resource not found
* Invalid requests

## 🗄️ Database

MySQL is used as the relational database.

Database:

```text
hospitalDB
```

Spring Data JPA and Hibernate are used for database interaction and ORM.

## ▶️ How to Run

1. Clone the repository

```terminal
git clone https://github.com/abhirajput-19/hospital-management-system.git
```

2. Configure MySQL

Create a database:

```text
CREATE DATABASE hospitalDB;
```

Configure your database credentials using environment variables.

Example:

```env
spring.datasource.url=jdbc:mysql://localhost:3306/hospitalDB
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}

jwt.secret=${JWT_SECRET}
```

3. Run the application

Using Maven:

```text
./mvnw spring-boot:run
```

Or run the main Spring Boot application from IntelliJ IDEA.

The application runs on:

```text
http://localhost:8080
```

## 🧪 API Testing

APIs were tested using Postman.

The project includes testing for:

* Authentication
* CRUD operations
* Validation
* Exception handling
* Role-based authorization
* Entity relationships

## 👨‍💻 Author

Abhinandan

GitHub: https://github.com/abhirajput-19


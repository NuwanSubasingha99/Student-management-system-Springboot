# 🧑‍🎓 Student Management REST API

A simple **Spring Boot REST API** for managing students using **Spring Data JPA** and **PostgreSQL**.
This API provides CRUD (Create, Read, Update, Delete) operations with **input validation**, **service layer architecture**, and **global exception handling**.

---

## 📑 Table of Contents

* [Overview](#overview)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Project Structure](#project-structure)
* [Entity Design](#entity-design)
* [API Endpoints](#api-endpoints)
* [Validation Rules](#validation-rules)
* [Global Exception Handling](#global-exception-handling)
* [Setup and Run Instructions](#setup-and-run-instructions)
* [Example API Requests](#example-api-requests)
* [HTTP Status Codes](#http-status-codes)

---

## 🧩 Overview

This project demonstrates how to build a **RESTful API** using **Spring Boot** to manage student data.
It uses **PostgreSQL** as the database and **Spring Data JPA** for ORM.
A clean **Service Layer** separates business logic from the Controller, and validation ensures reliable input.

---

## ⚙️ Technologies Used

* **Java 17+**
* **Spring Boot 3+**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate Validation**
* **PostgreSQL**
* **Maven**

---

## 🧱 Features

| Feature           | HTTP Method | Endpoint             | Description                       |
| ----------------- | ----------- | -------------------- | --------------------------------- |
| Add Student       | `POST`      | `/api/students`      | Create a new student record       |
| Get All Students  | `GET`       | `/api/students`      | Retrieve all students             |
| Get Student by ID | `GET`       | `/api/students/{id}` | Retrieve a specific student by ID |
| Update Student    | `PUT`       | `/api/students/{id}` | Update student details            |
| Delete Student    | `DELETE`    | `/api/students/{id}` | Delete a student by ID            |

---

## 🗂️ Project Structure

```
student-management/
├── src/
│   ├── main/
│   │   ├── java/com/example/studentmanagement/
│   │   │   ├── controller/
│   │   │   │   └── StudentController.java
│   │   │   ├── entity/
│   │   │   │   └── Student.java
│   │   │   ├── repository/
│   │   │   │   └── StudentRepository.java
│   │   │   ├── service/
│   │   │   │   ├── StudentService.java
│   │   │   │   └── StudentServiceImpl.java
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── ResourceNotFoundException.java
│   │   │   └── StudentManagementApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
├── pom.xml
└── README.md
```

---

## 🧠 Entity Design

### **Student.java**

```java
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Course cannot be empty")
    private String course;

    @Min(value = 18, message = "Age must be greater than 18")
    private int age;

    // Getters & Setters
}
```

---

## 🧩 API Endpoints

### ➕ Add Student

**POST** `/api/students`

**Request Body:**

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "course": "Computer Science",
  "age": 22
}
```

**Response (201 Created):**

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "course": "Computer Science",
  "age": 22
}
```

---

### 📋 Get All Students

**GET** `/api/students`

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "course": "Computer Science",
    "age": 22
  }
]
```

---

### 🔍 Get Student by ID

**GET** `/api/students/{id}`

**Response (200 OK):**

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "course": "Computer Science",
  "age": 22
}
```

**If not found (404):**

```json
{
  "error": "Student not found with id 10"
}
```

---

### ✏️ Update Student

**PUT** `/api/students/{id}`

**Request Body:**

```json
{
  "name": "John Smith",
  "email": "johnsmith@example.com",
  "course": "Software Engineering",
  "age": 23
}
```

**Response (200 OK):**

```json
{
  "id": 1,
  "name": "John Smith",
  "email": "johnsmith@example.com",
  "course": "Software Engineering",
  "age": 23
}
```

---

### 🗑️ Delete Student

**DELETE** `/api/students/{id}`

**Response (204 No Content):**

```
(no body)
```

---

## ✅ Validation Rules

| Field    | Constraint            | Description                |
| -------- | --------------------- | -------------------------- |
| `name`   | `@NotBlank`           | Must not be empty          |
| `email`  | `@Email`, `@NotBlank` | Must be valid email format |
| `course` | `@NotBlank`           | Must not be empty          |
| `age`    | `@Min(18)`            | Must be greater than 18    |

If validation fails, API returns `400 Bad Request`:

```json
{
  "email": "Invalid email format",
  "age": "Age must be greater than 18"
}
```

---

## ⚠️ Global Exception Handling

**Handled Exceptions:**

* `ResourceNotFoundException` → 404 Not Found
* `MethodArgumentNotValidException` → 400 Bad Request

### Example Response:

```json
{
  "error": "Student not found with id 5"
}
```

Or for validation:

```json
{
  "email": "Invalid email format",
  "name": "Name cannot be empty"
}
```

---

## 🛠️ Setup and Run Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/student-management.git
cd student-management
```

### 2. Create a PostgreSQL Database

```sql
CREATE DATABASE studentdb;
```

### 3. Configure Database in `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/studentdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Build and Run the Project

```bash
mvn spring-boot:run
```

### 5. Access the API

```
http://localhost:8080/api/students
```

---

## 🧪 Example API Requests (using cURL)

### Add a Student

```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Jane Doe","email":"jane@example.com","course":"AI & ML","age":21}'
```

### Get All Students

```bash
curl -X GET http://localhost:8080/api/students
```

### Update a Student

```bash
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Jane Smith","email":"jane.smith@example.com","course":"Data Science","age":23}'
```

### Delete a Student

```bash
curl -X DELETE http://localhost:8080/api/students/1
```

---

## 🌐 HTTP Status Codes

| Status Code         | Meaning                                     |
| ------------------- | ------------------------------------------- |
| **201 Created**     | Successfully created a student              |
| **200 OK**          | Successfully retrieved or updated a student |
| **204 No Content**  | Successfully deleted a student              |
| **400 Bad Request** | Validation failed or invalid input          |
| **404 Not Found**   | Student not found                           |

---

## 🧾 License

This project is created for learning and demonstration purposes.
You may freely use and modify it for educational or personal projects.


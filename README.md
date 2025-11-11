# Task Management API (TODO-API)

![Status](https://img.shields.io/badge/Status-COMPLETED-brightgreen)
![Version](https://img.shields.io/badge/Version-v1.0-blue)

---

## 1. Introduction

This project is a robust REST API for task management (a "TODO app"), built from scratch. The main objective was to learn and demonstrate the fundamental concepts of modern backend development using the Spring Boot ecosystem.

It is not just a basic CRUD; it is a complete API that implements a clean architecture and production-level features. This includes a secure authentication and authorization system (JWT), centralized error handling, ownership-level security (a user can only see their own tasks), and interactive API documentation.

---

## 2. Features

This API implements a large number of professional-grade features:

* **Complete Authentication & Authorization:**
  * Registration (`/register`) and login (`/login`) system using **Spring Security 6**.
  * **JSON Web Token (JWT)** generation and validation for a *stateless* API.
  * Password hashing using **BCrypt** (`PasswordEncoder`).
  * Dedicated endpoint (`/me/password`) for secure password updates (verifying the old password).

* **Robust Security (Authorization):**
  * **Role-Based Access Control (RBAC):** The API is ready for different roles (e.g., `ROLE_ADMIN`, `ROLE_USER`).
  * **Ownership-Level Security (IDOR Protection):** A standard user (`ROLE_USER`) **can only** view, modify, or delete tasks that belong to them. They cannot access or guess the tasks of other users.
  * User management endpoints (`/api/v1/users/me`) are protected and only operate on the currently authenticated user.

* **Professional Error Handling:**
  * A centralized **`GlobalExceptionHandler`** (`@ControllerAdvice`) that intercepts all application exceptions.
  * Responds with a clean and consistent error JSON (`ExceptionResponse`) for all errors (400, 401, 403, 404, 409, 500).
  * Validation error handling (`MethodArgumentNotValidException`) that returns a JSON object of all fields that failed.
  * Custom JWT exception handling (`CustomAuthenticationEntryPoint`) for 401 errors.

* **Clean & Modern API Design:**
  * **Clean Architecture:** Business logic is fully separated into layers (Controller, Service, Repository, Mappers).
  * **DTOs (Data Transfer Objects):** Use of Request and Response DTOs to ensure the API never exposes JPA database entities.
  * **Mapper Pattern:** Used to cleanly convert between Entities and DTOs.
  * **Efficient Repository Queries:** Use of Spring Data JPA derived query methods (e.g., `findByIdAndUser`) to perform security logic at the database level.
  * **Semantic Endpoints:** Clear and RESTful API routes (e.g., `/me`, `/tasks/{id}/complete`).

* **API Documentation:**
  * Automatic generation of interactive documentation using **SpringDoc (OpenAPI 3)**.
  * Swagger UI configuration to support JWT authentication.

---
 
## 3. Tech Stack

* **Framework:** Spring Boot 3
* **Security:** Spring Security 6 (with JWT Authentication)
* **Database:** Spring Data JPA (Hibernate)
* **Driver:** MySQL (or any JDBC-compatible database)
* **Validation:** Spring Boot Starter Validation (`@Valid`)
* **Tokens:** `io.jsonwebtoken:jjwt`
* **Documentation:** `org.springdoc:springdoc-openapi-starter-webmvc-ui`
* **Utilities:** Lombok

---

---

## 4. Getting Started

To run this project locally, follow these steps:

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/YOUR_USER/YOUR_REPO.git](https://github.com/YOUR_USER/YOUR_REPO.git)
    cd YOUR_REPO
    ```

2.  **Configure the Database and JWT Secret:**
    Open the `src/main/resources/application.properties` file and set your variables. You will need:
    * Your MySQL database URL, username, and password.
    * Your **JWT Secret Key** (a long, random string).

    ```properties
    # MySQL Database
    spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
    spring.datasource.username=root
    spring.datasource.password=your_password
    
    # JPA
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

---

## 5. API Documentation & Usage Flow

The documentation is live and generated automatically. You can access it while the application is running at:

```bash
    http://localhost:8080/swagger-ui.html
```

To test the protected endpoints, follow this visual flow:

1. **Register a new user:**
First, expand the auth-controller and use the POST /api/v1/auth/register endpoint to create a new user.

<img src="https://github.com/JorgeRamos2001/TODO-API/blob/main/.github/assets/swagger-auth-controller.png" width="100%">

<img src="https://github.com/JorgeRamos2001/TODO-API/blob/main/.github/assets/swagger-auth-register.png" width="100%">

2 . **Login and get your token:**
Next, use the POST /api/v1/auth/login endpoint with the user you just created. Execute it, and you will get back your JWT token in the response body.

<img src="https://github.com/JorgeRamos2001/TODO-API/blob/main/.github/assets/swagger-auth-token.png" width="100%">

3. **Click the "Authorize" button:**
Now, click the green "Authorize" 🔒 button at the top right of the page.

<img src="https://github.com/JorgeRamos2001/TODO-API/blob/main/.github/assets/swagger-authorize-button.png" width="100%">

4. **Enter your Bearer Token:**
Copy the token (without the quotes) from Step 2. In the popup, type Bearer  (with a space) and then paste your token. Click "Authorize" and close the window.

<img src="https://github.com/JorgeRamos2001/TODO-API/blob/main/.github/assets/swagger-authorize-token.png" width="100%">

5. **Test a protected endpoint!**
You are now authenticated. All endpoints with a 🔒 icon (like those in user-controller and task-controller) will now work. You can test them!

<img src="https://github.com/JorgeRamos2001/TODO-API/blob/main/.github/assets/swagger-protected-url.png" width="100%">

---

---

## 6. Author

This project was developed by **Jorge Alexis Ramos Ramos**.

Let's connect!

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-0077B5?logo=linkedin&logoColor=white)](https://www.linkedin.com/in/jorge-ramos-5b0a3427b/)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-181717?logo=github&logoColor=white)](https://github.com/JorgeRamos2001)

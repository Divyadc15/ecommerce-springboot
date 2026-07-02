# 🛒 E-Commerce Backend API:

A secure and scalable RESTful E-Commerce Backend built using **Spring Boot**. The application provides **JWT Authentication**, **Role-Based Authorization (ADMIN & USER)**, **Product Management**, **Category Management**, **Shopping Cart**, **Order Management**, **Image Upload**, **Pagination**, **Product Search**, and **Swagger API Documentation**.


#📌 Project Overview:

This project is a backend REST API for an E-Commerce application developed using **Spring Boot**. It follows a layered architecture consisting of **Controller**, **Service**, **Repository**, and **Entity** layers.

The application includes secure authentication using **JWT**, role-based access control, product and category management, shopping cart functionality, order processing, global exception handling, validation, pagination, and search functionality.

# 🚀 Features:

## 🔐 Authentication & Security

- User Registration
- User Login
- JWT Authentication
- Role-Based Authorization (ADMIN & USER)
- BCrypt Password Encryption

## 📦 Product Management

- Add Product
- Update Product
- Delete Product
- View All Products
- Get Product By ID
- Product Search
- Pagination & Sorting

## 📂 Category Management

- Add Category
- Update Category
- Delete Category
- View Categories

## 🛒 Shopping Cart

- Add Product to Cart
- View Cart
- Remove Cart Item
- Clear Cart

## 📋 Order Management

- Place Order
- View My Orders
- View Order Details
- Cancel Order

## ⚙️ Other Features:

- Image Upload
- DTO Pattern
- Bean Validation
- Global Exception Handling
- Custom Exceptions
- Swagger API Documentation


# 🛠️ Technology Stack:

## Backend

- Java 21
- Spring Boot 3.5.3
- Spring Security
- Spring Data JPA (Hibernate)
- JWT (JSON Web Token)

## Database

- MySQL

## Build Tool

- Maven

## API Testing

- Postman
- Swagger UI (OpenAPI)

## IDE

- Spring Tool Suite (STS)


# 🏗️ Project Architecture:


                Client (Postman / React)
                         │
                         ▼
                Spring Boot REST API
                         │
      ┌──────────────────┼──────────────────┐
      │                  │                  │
 Authentication      Business Logic     Validation
      │                  │                  │
      └──────────────────┼──────────────────┘
                         │
                    Spring Data JPA
                         │
                         ▼
                      MySQL Database


# 📁 Project Structure:


src
├── controller
├── service
├── repository
├── entity
├── dto
├── config
├── exception
├── resources
└── EcommerceApplication.java


# 📚 REST API Modules:

## Authentication

| Method | Endpoint |
|--------|----------|
| POST | /auth/register |
| POST | /auth/login |

---

## Category

| Method | Endpoint |
|--------|----------|
| GET | /categories |
| POST | /categories |
| PUT | /categories/{id} |
| DELETE | /categories/{id} |

---

## Product

| Method | Endpoint |
|--------|----------|
| GET | /products |
| GET | /products/{id} |
| GET | /products/search |
| GET | /products/category/{id} |
| POST | /products |
| PUT | /products/{id} |
| DELETE | /products/{id} |

---

## Cart

| Method | Endpoint |
|--------|----------|
| POST | /cart/add |
| GET | /cart |
| DELETE | /cart/remove/{id} |
| DELETE | /cart/clear |

---

## Orders

| Method | Endpoint |
|--------|----------|
| POST | /orders |
| GET | /orders |
| GET | /orders/{id} |
| PUT | /orders/cancel/{id} |

---

# 🚀 Getting Started:

## 1️⃣ Clone Repository

```bash
git clone https://github.com/YOUR_GITHUB_USERNAME/ecommerce.git

## 2️> Create Database

```sql
CREATE DATABASE ecommerce;
```

## 3️> Configure Application

Update the following file:

```text
src/main/resources/application.properties
```

Configure:

- MySQL Username
- MySQL Password
- JWT Secret

## 4️> Run the Application

Run:

```text
EcommerceApplication.java
```

or

```bash
mvn spring-boot:run
```

---

# 📖 API Documentation:

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 🚀 Future Enhancements:

- React Frontend
- Payment Gateway Integration
- Wishlist Module
- Product Reviews & Ratings
- Email Notifications
- Docker Support
- Cloud Deployment

---

# 👨‍💻 Author:

**Divya Chaurasiya**

B.Tech Computer Science Engineering

Java Backend Developer | Spring Boot | REST APIs | MySQL

---

## ⭐ If you found this project useful, consider giving it a star on GitHub.
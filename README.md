# 🏦 Personal Finance Tracker - Backend API

[![Java](https://img.shields.io/badge/Java-17-007396?logo=java)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.5-6DB33F?logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-6.1.5-6DB33F?logo=spring-security)](https://spring.io/projects/spring-security)
[![JWT](https://img.shields.io/badge/JWT-Auth-000000?logo=json-web-tokens)](https://jwt.io/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-4169E1?logo=postgresql)](https://www.postgresql.org/)
[![Flyway](https://img.shields.io/badge/Flyway-9.22.0-CC0000?logo=flyway)](https://flywaydb.org/)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?logo=swagger)](https://swagger.io/)
[![JUnit](https://img.shields.io/badge/JUnit-5.10.0-25A162?logo=junit5)](https://junit.org/junit5/)
[![Render](https://img.shields.io/badge/Deployed_on-Render-46E3B7?logo=render)](https://render.com)

> A robust, production-ready REST API for personal finance management with comprehensive security, documentation, and testing.

## 📋 Table of Contents

- [Features](#-features)
- [Architecture](#-architecture)
- [Tech Stack](#-tech-stack)
- [Getting Started](#-getting-started)
- [API Documentation](#-api-documentation)
- [Database Schema](#-database-schema)
- [Security](#-security)
- [Error Handling](#-error-handling)
- [Testing](#-testing)
- [Deployment](#-deployment)
- [Performance](#-performance)
- [Monitoring](#-monitoring)
- [Contributing](#-contributing)
- [License](#-license)

## ✨ Features

### 🚀 Core Features
- **User Authentication & Authorization**
  - JWT-based authentication
  - Role-based access control (USER/ADMIN)
  - Secure password encryption (BCrypt)
  - Token validation and refresh

- **Transaction Management**
  - Create, read, update, delete transactions
  - Income/Expense categorization
  - Date-based filtering
  - Monthly financial reports

- **Advanced Features**
  - Swagger/OpenAPI documentation
  - Global exception handling
  - Request/Response DTOs
  - Service interface for loose coupling
  - Flyway database migrations
  - Multi-profile configuration (dev/prod)

### 🔒 Security Features
- JWT token-based authentication
- Password encryption with BCrypt
- Role-based method security (@PreAuthorize)
- CSRF protection (disabled for REST APIs)
- CORS configuration
- SQL injection prevention via JPA
- XSS protection

### 📊 API Features
- RESTful API design
- DTO pattern for data transfer
- Pagination support
- Sorting and filtering
- Validation annotations
- Custom exception handling
- Global error responses


## 🛠️ Tech Stack

### Core Framework
| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming Language |
| Spring Boot | 3.1.5 | Application Framework |
| Spring Security | 6.1.5 | Authentication & Authorization |
| Spring Data JPA | 3.1.5 | Database Access |
| Hibernate | 6.2.13 | ORM Framework |

### Database & Migration
| Technology | Version | Purpose |
|------------|---------|---------|
| PostgreSQL | 15 | Production Database |
| H2 Database | 2.2.220 | Development Database |
| Flyway | 9.22.0 | Database Migration |

### Security & Utilities
| Technology | Version | Purpose |
|------------|---------|---------|
| JJWT | 0.11.5 | JWT Token Management |
| BCrypt | - | Password Encryption |
| Lombok | 1.18.30 | Boilerplate Reduction |
| ModelMapper | 3.1.1 | DTO Mapping |

### Documentation & Testing
| Technology | Version | Purpose |
|------------|---------|---------|
| SpringDoc OpenAPI | 2.2.0 | API Documentation (Swagger) |
| JUnit 5 | 5.10.0 | Unit Testing |
| Mockito | 5.5.0 | Mocking Framework |
| TestContainers | 1.19.0 | Integration Testing |
| Spring Boot Test | 3.1.5 | Integration Testing |

### Build & Deployment
| Technology | Version | Purpose |
|------------|---------|---------|
| Maven | 3.9.0 | Build Automation |
| Docker | - | Containerization |
| Render | - | Cloud Deployment |
| GitHub Actions | - | CI/CD Pipeline |

## 🚀 Getting Started

### Prerequisites
- JDK 17 or higher
- Maven 3.9+
- PostgreSQL 15+ (for production)
- Git
- Docker (optional)

# Email Sender Service

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)
![MySQL](https://img.shields.io/badge/MySQL-8-blue)
![Flyway](https://img.shields.io/badge/Flyway-11.8.0-red)
![Tests](https://img.shields.io/badge/Tests-JUnit5-green)

## 📋 Overview

The Email Sender is a modern, robust microservice built with Spring Boot that handles contact form submissions and email notifications. Designed with scalability and maintainability in mind, it offers a reliable solution for processing contact forms from websites and sending notifications to configured recipients.

## 🚀 Key Features

- **Contact Form Processing**: Validates and processes form submissions with comprehensive error handling
- **Email Delivery**: Sends formatted emails using Spring Mail
- **Recipient Management**: Database-backed recipient configuration for flexible notification routing
- **Health Monitoring**: Endpoints for application health monitoring
- **Cross-Origin Support**: Configured CORS for secure frontend integration
- **Containerization**: Docker support for consistent deployment
- **Database Migrations**: Automated schema management with Flyway

## 💻 Tech Stack

### Backend
- **Java 17**: Latest LTS version with modern language features
- **Spring Boot 3.4.3**: Latest version for building production-ready applications
    - Spring Web: RESTful API development
    - Spring Mail: Email service integration
    - Spring Data JPA: Database operations simplification
- **MySQL**: Relational database for recipient configuration
- **Flyway**: Database migration and version control
- **Docker**: Containerization for consistent deployment

### Testing
- **JUnit 5**: Comprehensive unit and integration testing
- **Spring Boot Test**: Simplified testing infrastructure

## 🏗️ Architecture

The application follows clean architecture principles with clear separation of concerns:

- **Controllers**: Handle HTTP requests and responses
    - `ContactFormController`: Processes form submissions
    - `HealthController`: Provides application health status

- **Services**: Implement business logic
    - `ContactFormService`: Validates and processes form data
    - `MailService`: Handles email composition and delivery

- **Repositories**: Manage data persistence
    - `RecipientRepository`: Manages email recipient configurations

- **Models**: Define data structures
    - `ContactForm`: Data model for contact form submissions
    - `Recipient`: Configuration for email recipients

- **Validation**: Business rule enforcement
    - Input validation
    - Recipient validation

## 🛠️ Software Engineering Practices

- **Clean Code**: Readability and maintainability as a priority
- **SOLID Principles**: Adherence to object-oriented design principles
- **Layered Architecture**: Clear separation of concerns
- **Dependency Injection**: Spring IoC container for loose coupling
- **Exception Handling**: Comprehensive error management
- **Database Migration**: Version-controlled schema evolution
- **Containerization**: Docker for consistent environment management
- **Unit Testing**: Thorough test coverage
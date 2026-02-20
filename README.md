# 🚗 Car Rental Backend Service

[![Java Version](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.java.net/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-PostGIS-blue.svg)](https://www.postgresql.org/)
[![Redis](https://img.shields.io/badge/Redis-Cache-red.svg)](https://redis.io/)
[![RabbitMQ](https://img.shields.io/badge/RabbitMQ-Messaging-orange.svg)](https://www.rabbitmq.com/)
[![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED.svg)](https://www.docker.com/)

A robust, scalable backend service for a car rental mobile application built with **Spring Boot 3**. This service
provides comprehensive APIs for user management, car listing, booking operations, and review systems with location-based
search capabilities.

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Installation & Setup](#-installation--setup)
- [Configuration](#-configuration)
- [Running the Application](#-running-the-application)
- [API Documentation](#-api-documentation)
- [Database Schema](#-database-schema)
- [Testing](#-testing)
- [Deployment](#-deployment)
- [Contributing](#-contributing)

---

## 🎯 Overview

This backend service powers a peer-to-peer car rental platform where vehicle owners can list their cars for rent and
customers can search, book, and review vehicles. The system supports:

- **Location-based car search** using PostGIS spatial queries
- **Real-time booking management** with conflict detection
- **Secure authentication** via JWT and OAuth2 (Google)
- **Asynchronous processing** with RabbitMQ for booking notifications
- **High-performance caching** with Redis

---

## ✨ Features

### User Management

- User registration and authentication (JWT-based)
- OAuth2 integration (Google Sign-In)
- Role-based access control (Customer, Owner, Admin)
- OTP verification via Email/SMS (Twilio)
- Profile management

### Car Management

- CRUD operations for car listings
- Multi-image upload to Cloudinary
- Location management with geospatial data
- Price configuration

### Booking System

- Real-time availability checking
- Time-slot conflict detection
- Booking status management (Pending → Confirmed → Completed/Cancelled)
- Total price calculation based on duration

### Review System

- Rating system (1-5 stars)
- Comments and feedback
- Review history per booking

### Search & Discovery

- Location-based car search (proximity queries)
- Filter by brand, year, province, price range
- Sorting capabilities (price, distance, rating)
- Pagination support

---

## 🛠 Tech Stack

### Core Framework

| Technology          | Version | Purpose                        |
|---------------------|---------|--------------------------------|
| **Java**            | 17      | Programming Language           |
| **Spring Boot**     | 3.5.6   | Application Framework          |
| **Spring Security** | 6.x     | Authentication & Authorization |
| **Spring Data JPA** | 3.x     | ORM & Database Access          |

### Database & Cache

| Technology     | Purpose                       |
|----------------|-------------------------------|
| **PostgreSQL** | Primary Database              |
| **PostGIS**    | Geospatial Queries            |
| **Redis**      | Session Cache & Token Storage |

### Messaging & Cloud

| Technology     | Purpose             |
|----------------|---------------------|
| **RabbitMQ**   | Async Message Queue |
| **Cloudinary** | Image Storage CDN   |
| **Twilio**     | SMS/OTP Service     |

### Security

| Technology     | Purpose                    |
|----------------|----------------------------|
| **JWT (JJWT)** | Token-based Authentication |
| **OAuth2**     | Social Login (Google)      |
| **BCrypt**     | Password Hashing           |

### DevOps

| Technology         | Purpose                       |
|--------------------|-------------------------------|
| **Docker**         | Containerization              |
| **Docker Compose** | Multi-container Orchestration |
| **Maven**          | Build Tool                    |

---

## 🏗 Architecture

The project follows **Clean Architecture** principles with clear separation of concerns:

```
┌─────────────────────────────────────────────────────────────┐
│                    Presentation Layer                        │
│              (Controllers, DTOs, REST APIs)                  │
├─────────────────────────────────────────────────────────────┤
│                    Application Layer                         │
│              (Services, Mappers, Business Logic)             │
├─────────────────────────────────────────────────────────────┤
│                      Domain Layer                            │
│           (Entities, Repositories, Enums, Constants)         │
├─────────────────────────────────────────────────────────────┤
│                   Infrastructure Layer                       │
│              (RabbitMQ, External Services)                   │
└─────────────────────────────────────────────────────────────┘
```

---

## 📁 Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/duydev/backend/
│   │   │   ├── BackendApplication.java          # Main entry point
│   │   │   │
│   │   │   ├── application/                     # Application Layer
│   │   │   │   ├── mapper/                      # Entity-DTO mappers
│   │   │   │   └── service/                     # Business logic services
│   │   │   │       ├── interfaceservice/        # Service interfaces
│   │   │   │       ├── AccountService.java      # User account operations
│   │   │   │       ├── AuthenticationServiceImpl.java
│   │   │   │       ├── ManagementCarsService.java
│   │   │   │       ├── RentationCarsService.java
│   │   │   │       └── ReviewBookingServiceImpl.java
│   │   │   │
│   │   │   ├── config/                          # Configuration
│   │   │   │   ├── AppConfig.java               # General app config
│   │   │   │   ├── CustomeFilterJwt.java        # JWT filter
│   │   │   │   ├── RabbitmqConfig.java          # Message queue config
│   │   │   │   ├── RedisConfig.java             # Cache config
│   │   │   │   └── TwilioConfig.java            # SMS service config
│   │   │   │
│   │   │   ├── domain/                          # Domain Layer
│   │   │   │   ├── constant/                    # Constants
│   │   │   │   ├── enums/                       # Enumerations
│   │   │   │   ├── model/                       # JPA Entities
│   │   │   │   │   ├── User.java
│   │   │   │   │   ├── CarsEntity.java
│   │   │   │   │   ├── BookingEntity.java
│   │   │   │   │   ├── ReviewsEntity.java
│   │   │   │   │   ├── RoleEntity.java
│   │   │   │   │   └── ...
│   │   │   │   └── repositories/                # JPA Repositories
│   │   │   │
│   │   │   ├── exception/                       # Exception Handling
│   │   │   │   ├── AppException.java
│   │   │   │   ├── EnumException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   │
│   │   │   ├── infrastructure/                  # Infrastructure Layer
│   │   │   │   └── rabbitmq/                    # Message producers/consumers
│   │   │   │
│   │   │   ├── presentation/                    # Presentation Layer
│   │   │   │   ├── controller/                  # REST Controllers
│   │   │   │   │   ├── AuthenticationController.java
│   │   │   │   │   ├── ManagementionCarsController.java
│   │   │   │   │   ├── RentionController.java
│   │   │   │   │   └── ReviewBookingController.java
│   │   │   │   └── dto/                         # Data Transfer Objects
│   │   │   │       ├── request/                 # Request DTOs
│   │   │   │       └── response/                # Response DTOs
│   │   │   │
│   │   │   └── util/                            # Utilities
│   │   │       ├── CloudinaryUtil.java          # Image upload helper
│   │   │       ├── EmailUtil.java               # Email sender
│   │   │       ├── JwtUtil.java                 # JWT operations
│   │   │       ├── NotificationUtil.java        # Push notifications
│   │   │       └── TwilioUtil.java              # SMS helper
│   │   │
│   │   └── resources/
│   │       ├── application.yml                  # Main config
│   │       ├── application-dev.yml              # Dev profile
│   │       └── application-test.yml             # Test profile
│   │
│   └── test/                                    # Test sources
│       └── java/com/duydev/backend/
│           ├── application/service/             # Service tests
│           ├── domain/repositories/             # Repository tests
│           └── presentation/controller/         # Controller tests
│
├── initDB/
│   ├── 01-schema.sql                            # Database schema
│   └── data_backup.sql                          # Seed data
│
├── docker-compose.yml                           # Local development
├── docker-compose-staging.yml                   # Staging environment
├── docker-compose-test.yml                      # Test environment
├── Dockerfile                                   # Multi-stage build
├── pom.xml                                      # Maven dependencies
└── README.md
```

---

## 📦 Prerequisites

Before running this application, ensure you have:

- **Java 17** or higher ([Download](https://adoptium.net/))
- **Maven 3.8+** ([Download](https://maven.apache.org/download.cgi))
- **Docker & Docker Compose** ([Download](https://www.docker.com/get-started))
- **PostgreSQL 14+** (or use Docker)
- **Redis** (or use Docker)
- **RabbitMQ** (or use Docker)

### External Services (Required)

- **Cloudinary Account** - For image storage
- **Twilio Account** - For SMS/OTP (optional)
- **Google Cloud Console** - For OAuth2 (optional)
- **SMTP Server** - For email notifications

---

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd backend
```

### 2. Environment Configuration

Create a `.env` file in the project root:

```env
# Database Configuration
DB_HOST=localhost
PORT_DB=5432
DB_NAME=car_rental_db
DB_USER=postgres
DB_PASSWORD=your_secure_password

# Redis Configuration
REDIS_HOST=localhost
PORT_REDIS=6379

# RabbitMQ Configuration
RABBITMQ_HOST=localhost
PORT_RABBITMQ=5672
RABBITMQ_USER=user
RABBITMQ_PASSWORD=password

# JWT Configuration
JWT_SECRET_KEY=your_256_bit_secret_key_here
JWT_EXPIRATION=86400000
JWT_REFRESH_EXPIRATION=604800000

# Cloudinary Configuration
CLOUDINARY_CLOUD_NAME=your_cloud_name
CLOUDINARY_API_KEY=your_api_key
CLOUDINARY_API_SECRET=your_api_secret

# Twilio Configuration (Optional)
TWILIO_ACCOUNT_SID=your_account_sid
TWILIO_AUTH_TOKEN=your_auth_token
TWILIO_PHONE_NUMBER=+1234567890

# Email Configuration
EMAIL_HOST=smtp.gmail.com
EMAIL_PORT=587
USER_NAME=your_email@gmail.com
EMAIL_PASSWORD=your_app_password

# OAuth2 Configuration (Optional)
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
BASE_URL=http://localhost:8080

# Application Port
PORT=8080
```

### 3. Start Infrastructure Services (Docker)

```bash
# Start PostgreSQL, Redis, and RabbitMQ
docker-compose up -d
```

This will start:

- **PostgreSQL** with PostGIS on port `5432`
- **Redis** on port `6379`
- **RabbitMQ** on ports `5672` (AMQP) and `15672` (Management UI)

### 4. Initialize Database

The database schema is automatically initialized from `initDB/01-schema.sql` when the PostgreSQL container starts.

---

## ⚙️ Configuration

### Application Profiles

| Profile | File                   | Usage                       |
|---------|------------------------|-----------------------------|
| `dev`   | `application-dev.yml`  | Local development (default) |
| `test`  | `application-test.yml` | Running tests               |
| `prod`  | `application-prod.yml` | Production deployment       |

Activate a profile:

```bash
# Via Maven
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Via Environment Variable
export SPRING_PROFILES_ACTIVE=dev
```

---

## ▶️ Running the Application

### Option 1: Using Maven (Development)

```bash
# Install dependencies
./mvnw clean install -DskipTests

# Run the application
./mvnw spring-boot:run
```

### Option 2: Using JAR

```bash
# Build the JAR
./mvnw clean package -DskipTests

# Run the JAR
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

### Option 3: Using Docker

```bash
# Build Docker image
docker build -t car-rental-backend:latest .

# Run container
docker run -p 8080:8080 --env-file .env car-rental-backend:latest
```

### Verify the Application

```bash
# Health check
curl http://localhost:8080/actuator/health

# Expected response
{"status":"UP"}
```

Access **RabbitMQ Management UI**: http://localhost:15672 (user/password)

---

## 📖 API Documentation

### Authentication Endpoints

| Method | Endpoint                  | Description            |
|--------|---------------------------|------------------------|
| `POST` | `/api/auth/register`      | Register new user      |
| `POST` | `/api/auth/login`         | User login             |
| `POST` | `/api/auth/refresh-token` | Refresh access token   |
| `POST` | `/api/auth/logout`        | User logout            |
| `GET`  | `/auth/google/callback`   | Google OAuth2 callback |

### Car Management Endpoints

| Method   | Endpoint         | Description                 |
|----------|------------------|-----------------------------|
| `GET`    | `/api/cars`      | Get all cars (with filters) |
| `GET`    | `/api/cars/{id}` | Get car details             |
| `POST`   | `/api/cars`      | Create new car listing      |
| `PUT`    | `/api/cars/{id}` | Update car                  |
| `DELETE` | `/api/cars/{id}` | Delete car                  |

### Booking Endpoints

| Method | Endpoint                    | Description              |
|--------|-----------------------------|--------------------------|
| `POST` | `/api/bookings`             | Create booking           |
| `GET`  | `/api/bookings/{id}`        | Get booking details      |
| `PUT`  | `/api/bookings/{id}/status` | Update booking status    |
| `GET`  | `/api/bookings/user`        | Get user's bookings      |
| `GET`  | `/api/bookings/owner`       | Get owner's car bookings |

### Review Endpoints

| Method | Endpoint                   | Description     |
|--------|----------------------------|-----------------|
| `POST` | `/api/reviews`             | Create review   |
| `GET`  | `/api/reviews/car/{carId}` | Get car reviews |

### Account Endpoints

| Method | Endpoint                     | Description           |
|--------|------------------------------|-----------------------|
| `GET`  | `/api/account/me`            | Get current user info |
| `PUT`  | `/api/account/me`            | Update user info      |
| `PUT`  | `/api/account/upgrade-owner` | Upgrade to owner role |

---

## 🗄 Database Schema

### Entity Relationship Diagram

```
┌─────────────┐       ┌─────────────────┐       ┌─────────────┐
│   tbl_user  │───────│ tbl_user_has_   │───────│  tbl_role   │
│             │       │     role        │       │             │
└─────────────┘       └─────────────────┘       └─────────────┘
       │                                               │
       │                                               │
       ▼                                               ▼
┌─────────────┐                               ┌─────────────────┐
│  tbl_cars   │                               │ tbl_role_has_   │
│             │                               │   permission    │
└─────────────┘                               └─────────────────┘
       │                                               │
       │                                               ▼
       ▼                                       ┌─────────────┐
┌─────────────┐                               │tbl_permission│
│tbl_bookings │                               └─────────────┘
└─────────────┘
       │
       ▼
┌─────────────┐
│ tbl_reviews │
└─────────────┘
```

### Key Tables

| Table           | Description                                          |
|-----------------|------------------------------------------------------|
| `tbl_user`      | User accounts with status (ACTIVE, INACTIVE, BANNED) |
| `tbl_role`      | Roles (CUSTOMER, OWNER, ADMIN)                       |
| `tbl_cars`      | Car listings with JSONB images                       |
| `tbl_locations` | Geospatial location data                             |
| `tbl_bookings`  | Rental bookings with time slots                      |
| `tbl_reviews`   | Rating and comments                                  |
| `tbl_token`     | JWT token storage                                    |
| `tbl_otp`       | OTP codes for verification                           |

---

## 🧪 Testing

### Run Unit Tests

```bash
./mvnw test
```

### Run Integration Tests

```bash
./mvnw verify
```

### Test with Coverage

```bash
./mvnw test jacoco:report
```

View coverage report: `target/site/jacoco/index.html`

---

## 🐳 Deployment

### Docker Compose (Full Stack)

```bash
# Production
docker-compose -f docker-compose.yml up -d

# Staging
docker-compose -f docker-compose-staging.yml up -d
```

### Environment Variables for Production

Ensure all sensitive credentials are configured via environment variables or a secrets manager:

```bash
export JWT_SECRET_KEY=<production_secret>
export DB_PASSWORD=<production_db_password>
export CLOUDINARY_API_SECRET=<production_cloudinary_secret>
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Duy Dev**

- GitHub: [@duydev](https://github.com/duydev)

---

<p align="center">
  Made with ❤️ using Spring Boot
</p>


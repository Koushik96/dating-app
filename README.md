# ❤️ Dating App

A full-stack dating application built using **Java Spring Boot, Angular, and MySQL**. The application allows users to register, log in, discover other users, like profiles, and find matches.

## 🚀 Features

- User registration
- User login with password hashing using BCrypt
- View registered user profiles
- Discover other users
- Like and unlike profiles
- Match management
- RESTful APIs
- Global exception handling
- Angular-based user interface

## 🛠️ Tech Stack

**Backend**
- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- Hibernate
- Maven
- REST APIs

**Frontend**
- Angular
- TypeScript
- HTML
- CSS

**Database**
- MySQL 8.4

**Tools**
- Eclipse IDE
- Visual Studio Code
- Git & GitHub
- Postman

## 📁 Project Structure

```text
dating-app/
│
├── dating/                  # Spring Boot Backend
│   ├── src/main/java/
│   │   └── com/koushik/dating/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── entity/
│   │       ├── exception/
│   │       ├── repository/
│   │       └── service/
│   │
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
└── dating-frontend/         # Angular Frontend
    ├── public/
    ├── src/
    │   └── app/
    ├── package.json
    └── angular.json
```

## ⚙️ Getting Started

### Prerequisites

Make sure you have installed:

- Java 21
- MySQL 8.4
- Node.js and npm
- Angular CLI
- Git

### 1. Clone the repository

```bash
git clone https://github.com/Koushik96/dating-app.git
cd dating-app
```

### 2. Configure MySQL

Create the database:

```sql
CREATE DATABASE dating;
```

Configure these environment variables for the backend:

```text
DB_URL=jdbc:mysql://localhost:3306/dating
DB_USERNAME=root
DB_PASSWORD=your_mysql_password
```

The application reads database credentials from environment variables.

### 3. Run the Spring Boot backend

Open a terminal inside the `dating` folder.

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Backend default address:

```text
http://localhost:8080
```

### 4. Run the Angular frontend

Open another terminal inside the `dating-frontend` folder.

Install dependencies:

```bash
npm install
```

Start the Angular application:

```bash
npx ng serve
```

Frontend default address:

```text
http://localhost:4200
```

## 🔐 Security

- User passwords are hashed using BCrypt.
- Database credentials are configured through environment variables.
- Never commit real passwords, API keys, or other secrets.

## 🚧 Future Improvements

- Profile photo upload and cloud storage
- Public cloud deployment
- Improved user profile management
- Additional authentication and security improvements
- Enhanced user discovery experience

## 👨‍💻 Developer

**Koushik Dolai**

Java Backend Developer | Spring Boot | REST APIs | Angular | MySQL

GitHub: [Koushik96](https://github.com/Koushik96)

---

*This project is being actively developed as a full-stack application to strengthen practical skills in Java backend development and frontend integration.*
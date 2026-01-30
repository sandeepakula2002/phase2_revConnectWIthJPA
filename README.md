# RevConnect – Phase 2 (Spring Boot + Spring Data JPA)

RevConnect is a **console-based social networking application** developed as part of **Phase 2** of the project.
This phase focuses on migrating from **plain Java + JDBC** to **Spring Boot with Spring Data JPA**, while enforcing **clean architecture**, **layered design**, and **separation of concerns**.

---

## 🚀 Tech Stack

* Java 17+
* Spring Boot 3.x
* Spring Data JPA
* Hibernate ORM
* MySQL
* Maven
* HikariCP
* Lombok
* Log4j2

---

## 🧱 Architecture

The application follows a **layered architecture**:

**Entity → Repository → Service → Console UI**

### ✅ Key Design Rules Followed

* Business rules and validations handled in the **Service layer**
* Used **Spring Dependency Injection** (`@Autowired` / constructor injection)
* Clean separation between **DB, business logic, and UI**
* No SQL queries written manually
* Console-based UI implemented using **CommandLineRunner**

---

## 📦 Modules & Responsibilities

### 🧩 Entity Layer

* JPA entities mapped using annotations (`@Entity`, `@Id`, etc.)
* Relationships handled via IDs
* Enums used for domain safety (e.g., `AccountType`, `ConnectionStatus`)

### 🗄 Repository Layer

* Uses `JpaRepository`
* No manual SQL queries
* Derived query methods (`findBy`, `existsBy`, `deleteBy`)
* Responsible for all database interactions

### ⚙️ Service Layer

* Contains complete business logic
* Performs validations (user exists, post exists, duplicate checks, etc.)
* Throws exceptions instead of printing messages
* No UI or database-specific code

### 🖥 Console UI Layer

* Handles user input and output
* Displays menus and options
* Catches and displays service-layer exceptions
* Implemented using `CommandLineRunner`

---

## ✨ Features

### 👤 User Management

* Register
* Login
* Logout

### 📝 Posts

* Create post
* View own posts
* Delete post

### ❤️ Likes

* Like a post
* Unlike a post
* Prevent duplicate likes
* View like count

### 💬 Comments

* Add comment to a post
* View comments on a post
* Delete own comment

### 🤝 Connections

* Send connection request
* View pending requests
* Accept request
* Reject request
* View accepted connections

---

## 🧪 Testing

* Unit tests written using **JUnit 5** and **Mockito**
* Service-level testing with mocked repositories
* Covers core services:

  * UserService
  * PostService
  * LikeService
  * CommentService
  * ConnectionService

---

## 🖥 Sample Console Flow

```
--- RevConnect ---

1. Register
2. Login

--- Main Menu ---
1. Create Post
2. View My Posts
3. Like Post
4. Unlike Post
5. Delete Post
6. Add Comment
7. View Comments
8. Delete Comment
9. Send Connection Request
10. View Pending Requests
11. Accept Request
12. Reject Request
13. View Connections
14. Logout
```

---

## 📌 Notes

* This is a **console-based backend-focused project**
* Designed to demonstrate Spring Boot fundamentals and clean architecture
* Easily extendable to REST APIs in future phases

---

## 👨‍💻 Author
Sandeep Akula

**RevConnect – Phase 2**
Spring Boot & Spring Data JPA Project

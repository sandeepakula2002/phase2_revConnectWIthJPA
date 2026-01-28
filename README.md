# **RevConnect - Social Media Console Application**

## **📋 Project Overview**
RevConnect is a **Spring Boot-based console application** that simulates a social media platform with user registration, posts, likes, comments, and connection management.

## **🚀 Features**

### **User Management**
- ✅ User Registration with email, password, and account type
- ✅ User Login/Logout functionality
- ✅ Three account types: PERSONAL, CREATOR, BUSINESS

### **Post Management**
- ✅ Create new posts
- ✅ View your own posts
- ✅ Delete your posts
- ✅ Like/unlike posts
- ✅ View like counts

### **Comment System**
- ✅ Add comments to posts
- ✅ View comments on posts
- ✅ Delete your comments

### **Connection System**
- ✅ Send connection requests to other users
- ✅ View pending connection requests
- ✅ Accept/Reject connection requests
- ✅ View your connections list

## **🛠️ Technology Stack**

- **Java 17**
- **Spring Boot 4.0.2**
- **Spring Data JPA**
- **MySQL 8.0+ Database**
- **Maven** (Build Tool)
- **Hibernate** (ORM)

## **📁 Project Structure**

```
revconnect-springboot/
├── src/main/java/com/revconnect/
│   ├── RevconnectSpringbootApplication.java  # Main application class
│   ├── console/
│   │   └── ConsoleUI.java                    # Console user interface
│   ├── models/                               # Entity classes
│   │   ├── User.java
│   │   ├── Post.java
│   │   ├── Like.java
│   │   ├── Comment.java
│   │   └── Connection.java
│   ├── repository/                           # Repository interfaces
│   │   ├── UserRepository.java
│   │   ├── PostRepository.java
│   │   ├── LikeRepository.java
│   │   ├── CommentRepository.java
│   │   └── ConnectionRepository.java
│   └── service/                              # Service classes
│       ├── UserService.java
│       ├── PostService.java
│       ├── LikeService.java
│       ├── CommentService.java
│       └── ConnectionService.java
├── src/main/resources/
│   ├── application.properties                # Configuration file
│   └── database-setup.sql                    # Database setup script
├── pom.xml                                   # Maven dependencies
└── README.md                                 # This file
```

## **⚙️ Prerequisites**

1. **Java 17** or higher
2. **MySQL 8.0+** database
3. **Maven** 3.6+ (or use included Maven Wrapper)
4. **IDE** (IntelliJ IDEA, Eclipse, or VS Code)

## **🔧 Installation & Setup**

### **1. Clone and Setup Database**
```sql
-- Create database
CREATE DATABASE revconnect;
USE revconnect;

-- Or use the provided database-setup.sql
```

### **2. Configure Database Connection**
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/revconnect
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### **3. Build and Run**

**Using Maven:**
```bash
# Clean and build
mvn clean compile

# Run the application
mvn spring-boot:run
```

**Using IDE:**
- Import as Maven project
- Run `RevconnectSpringbootApplication.java`

## **🎮 How to Use**

### **Start the Application**
```
=== RevConnect ===
1. Register
2. Login
3. Exit
Choose: 
```

### **Registration**
1. Choose option 1: Register
2. Enter email, password
3. Select account type: PERSONAL, CREATOR, or BUSINESS

### **Login**
1. Choose option 2: Login
2. Enter registered email and password

### **Main Menu Features**
After login, you'll see:
```
=== Main Menu ===
1. Create Post
2. View My Posts
3. Like Post
4. Add Comment
5. Send Connection
6. View Requests
7. View Connections
8. Logout
```

## **📊 Database Schema**

The application automatically creates these tables:
- **users**: User accounts
- **posts**: User posts
- **comments**: Comments on posts
- **likes**: Likes on posts
- **connections**: User connections/relationships

## **🔗 API Endpoints**

**Note:** This is a console application, not a REST API. All interactions happen through the console interface.

## **🔄 Application Flow**

1. **Authentication Phase**
   - User registers or logs in
   - Session maintains logged-in user

2. **Content Interaction**
   - Create/view posts
   - Like/unlike posts
   - Add/delete comments

3. **Social Networking**
   - Send connection requests
   - Manage pending requests
   - View connections

## **🛡️ Security Features**

- Password validation during login
- User authentication for all actions
- Ownership validation (users can only delete their own posts/comments)

## **🧪 Testing**

Run included tests:
```bash
mvn test
```

Test classes include:
- `UserServiceTest` - User registration and login tests

## **🔍 Troubleshooting**

### **Common Issues**

1. **Database Connection Error**
   - Check MySQL is running
   - Verify credentials in `application.properties`
   - Ensure database exists

2. **Hibernate Dialect Error**
   - Update dialect in `application.properties`
   - Use `org.hibernate.dialect.MySQLDialect`

3. **Build Failures**
   - Clean and rebuild: `mvn clean compile`
   - Check Java version compatibility

### **Logs**
- Application logs to console
- SQL logging can be enabled/disabled in `application.properties`

## **📈 Future Enhancements**

Potential features to add:
- [ ] Profile management
- [ ] Post sharing
- [ ] Notifications
- [ ] Search functionality
- [ ] Direct messaging
- [ ] Analytics dashboard

## **📄 License**

This project is for educational purposes.

## **👥 Contributors**

- Developed as a Spring Boot learning project

## **🙏 Acknowledgments**

- Spring Boot framework
- MySQL database
- Hibernate ORM
- Maven build tool

---

**💡 Tip:** The application uses **JPA auto-generation** for database tables. On first run, tables will be created automatically based on entity classes.

**Enjoy connecting with RevConnect!** 🚀

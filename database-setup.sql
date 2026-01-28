-- Create Database
CREATE DATABASE IF NOT EXISTS revconnect;
USE revconnect;

-- Create Tables (if not using JPA auto-create)
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    account_type VARCHAR(50),
    name VARCHAR(100),
    bio TEXT,
    location VARCHAR(100),
    website VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS posts (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_id BIGINT NOT NULL,
                                     content TEXT NOT NULL,
                                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS comments (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        user_id BIGINT NOT NULL,
                                        post_id BIGINT NOT NULL,
                                        text TEXT NOT NULL,
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS likes (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_id BIGINT NOT NULL,
                                     post_id BIGINT NOT NULL,
                                     UNIQUE KEY unique_like (user_id, post_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS connections (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           sender_id BIGINT NOT NULL,
                                           receiver_id BIGINT NOT NULL,
                                           status VARCHAR(50) DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY unique_connection (sender_id, receiver_id),
    FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES users(id) ON DELETE CASCADE
    );

-- Insert Sample Data (Optional)
INSERT INTO users (email, password, account_type, name) VALUES
                                                            ('john@example.com', 'password123', 'PERSONAL', 'John Doe'),
                                                            ('jane@example.com', 'password456', 'CREATOR', 'Jane Smith'),
                                                            ('bob@example.com', 'password789', 'BUSINESS', 'Bob Wilson');
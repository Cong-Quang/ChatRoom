CREATE DATABASE IF NOT EXISTS chatapp CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE chatapp;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    display_name VARCHAR(100) DEFAULT NULL,
    status ENUM('online', 'offline') DEFAULT 'offline',
    last_login TIMESTAMP NULL DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS friends (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    friend_id INT NOT NULL,
    status ENUM('pending', 'accepted', 'blocked') DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (friend_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS rooms (
    id INT AUTO_INCREMENT PRIMARY KEY,
    room_name VARCHAR(100) NOT NULL UNIQUE,
    created_by INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS room_members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    room_id INT NOT NULL,
    user_id INT NOT NULL,
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT NOT NULL,
    recipient_id INT DEFAULT NULL,
    room_id INT DEFAULT NULL,
    content TEXT NOT NULL,
    message_type ENUM('text', 'image', 'file', 'system') DEFAULT 'text',
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (recipient_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE
);

INSERT INTO users (username, password, display_name, status)
VALUES 
('admin', '123456', 'Admin', 'offline'),
('alice', '123456', 'Alice', 'offline'),
('bob', '123456', 'Bob', 'offline');

INSERT INTO friends (user_id, friend_id, status)
VALUES 
(1, 2, 'accepted'),
(1, 3, 'accepted'),
(2, 3, 'pending');

INSERT INTO rooms (room_name, created_by)
VALUES 
('General Chat', 1),
('Java Devs', 2);

INSERT INTO room_members (room_id, user_id)
VALUES 
(1, 1), (1, 2), (1, 3),
(2, 2), (2, 3);

INSERT INTO messages (sender_id, room_id, content)
VALUES 
(1, 1, 'Xin chào mọi người!'),
(2, 1, 'Chào admin!');

CREATE USER 'quang'@'localhost' IDENTIFIED BY 'deptrai@123!';
GRANT SELECT, INSERT, UPDATE, DELETE ON chatapp.users TO 'quang'@'localhost';
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'quang'@'localhost';

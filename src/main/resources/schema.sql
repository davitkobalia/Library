CREATE DATABASE IF NOT EXISTS library;

USE library;

CREATE TABLE IF NOT EXISTS role (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS patrons (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255),
                                       surname VARCHAR(255),
                                       email VARCHAR(255),
                                       phone_number VARCHAR(255),
                                       status BOOLEAN DEFAULT FALSE
);


CREATE TABLE IF NOT EXISTS user (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(255) NOT NULL UNIQUE,
                                    password VARCHAR(255) NOT NULL,
                                    role VARCHAR(255) NOT NULL,
                                    FOREIGN KEY (role) REFERENCES role(name)
);

CREATE TABLE IF NOT EXISTS books (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(255),
                                     author VARCHAR(255),
                                     isbn VARCHAR(255),
                                     genre VARCHAR(255),
                                     patron_id INT,
                                     reserved BOOLEAN DEFAULT FALSE,
                                     FOREIGN KEY (patron_id) REFERENCES patrons(id)
);

CREATE TABLE IF NOT EXISTS loan (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     book_id INT,
                                     patron_id INT,
                                     borrow_date DATE,
                                     return_date DATE,
                                     FOREIGN KEY (book_id) REFERENCES books(id),
                                     FOREIGN KEY (patron_id) REFERENCES patrons(id)
);

CREATE TABLE IF NOT EXISTS transactions (
                                            id INT AUTO_INCREMENT PRIMARY KEY,
                                            name VARCHAR(255),
                                            surname VARCHAR(255),
                                            title VARCHAR(255),
                                            author VARCHAR(255),
                                            transaction_type VARCHAR(255)
);

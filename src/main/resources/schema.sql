CREATE TABLE user (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL
);

CREATE TABLE book (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255) NOT NULL,
                       publisher VARCHAR(255) NOT NULL,
                       isbn VARCHAR(255) NOT NULL,
                       description TEXT
);

CREATE TABLE status (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        street VARCHAR(255) NOT NULL,
                        postal_code VARCHAR(20) NOT NULL,
                        city VARCHAR(255) NOT NULL,
                        phone_number VARCHAR(20) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        price DECIMAL(10,2) NOT NULL,
                        created_at TIMESTAMP NOT NULL,
                        status_id BIGINT,
                        user_id BIGINT,
                        FOREIGN KEY (status_id) REFERENCES status(id),
                        FOREIGN KEY (user_id) REFERENCES user(id)
);



CREATE TABLE order_detail (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               quantity INT NOT NULL,
                               unit_price DECIMAL(10,2) NOT NULL,
                               total_price DECIMAL(10,2) NOT NULL,
                               book_id BIGINT NOT NULL,
                               order_id BIGINT NOT NULL,
                               FOREIGN KEY (book_id) REFERENCES book(id),
                               FOREIGN KEY (order_id) REFERENCES orders(id)
);

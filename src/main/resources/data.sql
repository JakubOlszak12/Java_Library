-- Seed statuses
INSERT INTO statuses (name) VALUES
                                ('Pending'),
                                ('Processing'),
                                ('Completed');

-- Seed users
INSERT INTO users (username, password, email) VALUES
                                                  ('user1', '$2y$10$3RXNFvqqfhmKISiOuCXvbuanE4I.42mYNH0RVmS18ZdTUVXDlPPFO', 'user1@example.com'),
                                                  ('user2', '$2y$10$8wDow8Qbh0Fgwh09F57LCuKeKN/CSmMF6omHLrhuvcwcAr4XtNjsW', 'user2@example.com'),
                                                  ('user3', '$2a$10$GbbmKfOjN/pdKsAtg.qL2eZWb8f97GB0gIbM1BxvGZ.HA/W0bPoUq', 'user3@example.com'),
                                                  ('user4', '$2a$10$j0Uhi8Y1/kB34fzkJcrCw.vQ0mR9tH9c16BCJ0d6VwN/A9kUYlG36', 'user4@example.com'),
                                                  ('user5', '$2a$10$XWQqmt0b5GY2o8qegbouTe5Y40lhXVexz1Z2OGZkPUSUu/ZGUT6Lq', 'user5@example.com');
-- Seed books
INSERT INTO books (title, author, publisher, isbn, description) VALUES
                                                                    ('Book1', 'Author1', 'Publisher1', 'ISBN1', 'Description1'),
                                                                    ('Book2', 'Author2', 'Publisher2', 'ISBN2', 'Description2'),
                                                                    ('Book3', 'Author3', 'Publisher3', 'ISBN3', 'Description3'),
                                                                    ('Book4', 'Author4', 'Publisher4', 'ISBN4', 'Description4'),
                                                                    ('Book5', 'Author5', 'Publisher5', 'ISBN5', 'Description5'),
                                                                    ('Book6', 'Author6', 'Publisher6', 'ISBN6', 'Description6'),
                                                                    ('Book7', 'Author7', 'Publisher7', 'ISBN7', 'Description7'),
                                                                    ('Book8', 'Author8', 'Publisher8', 'ISBN8', 'Description8'),
                                                                    ('Book9', 'Author9', 'Publisher9', 'ISBN9', 'Description9'),
                                                                    ('Book10', 'Author10', 'Publisher10', 'ISBN10', 'Description10');

-- Seed orders
INSERT INTO orders (street, postal_code, city, phone_number, email, price, created_at, status_id, user_id) VALUES
                                                                                                               ('Street1', '12345', 'City1', '123456789', 'user1@example.com', 100.00, NOW(), 1, 1),
                                                                                                               ('Street2', '23456', 'City2', '234567890', 'user2@example.com', 200.00, NOW(), 2, 2),
                                                                                                               ('Street3', '34567', 'City3', '345678901', 'user3@example.com', 300.00, NOW(), 3, 3),
                                                                                                               ('Street4', '45678', 'City4', '456789012', 'user4@example.com', 400.00, NOW(), 1, 4),
                                                                                                               ('Street5', '56789', 'City5', '567890123', 'user5@example.com', 500.00, NOW(), 2, 5);

-- Seed order details
INSERT INTO order_details (quantity, unit_price, total_price, book_id, order_id) VALUES
                                                                                     (1, 50.00, 50.00, 1, 1),
                                                                                     (2, 50.00, 100.00, 2, 1),
                                                                                     (3, 50.00, 150.00, 3, 1),
                                                                                     (1, 50.00, 50.00, 4, 2),
                                                                                     (2, 50.00, 100.00, 5, 2),
                                                                                     (3, 50.00, 150.00, 6, 2),
                                                                                     (1, 50.00, 50.00, 7, 3),
                                                                                     (2, 50.00, 100.00, 8, 3),
                                                                                     (3, 50.00, 150.00, 9, 3),
                                                                                     (1, 50.00, 50.00, 10, 4),
                                                                                     (2, 50.00, 100.00, 1, 4),
                                                                                     (3, 50.00, 150.00, 2, 4),
                                                                                     (1, 50.00, 50.00, 3, 5),
                                                                                     (2, 50.00, 100.00, 4, 5),
                                                                                     (3, 50.00, 150.00, 5, 5);


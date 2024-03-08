CREATE DATABASE online_book_store;

USE online_book_store;

-- Creating User Table
CREATE TABLE users(
	id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(50),
    isAdmin BOOLEAN
);

-- Creating Book Table
CREATE TABLE books(
	id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    author VARCHAR(255),
    price DECIMAL(10, 2),
    quantity INT
);

-- Creating Cart Table
CREATE TABLE cart(
	id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    book_id INT,
    quantity INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);

alter table cart modify user_id int, add constraint uid FOREIGN KEY (user_id) REFERENCES users(id) on delete cascade;
alter table cart modify book_id int, add constraint bid FOREIGN KEY (book_id) REFERENCES books(id) on delete cascade;

-- Inserting into users
INSERT INTO `online_book_store`.`users` (`id`, `username`, `password`, `isAdmin`) VALUES ('101', 'admin', 'admin', '1');
INSERT INTO `online_book_store`.`users` (`id`, `username`, `password`, `isAdmin`) VALUES ('102', 'atul', 'atulv', '0');

-- Inserting into books
INSERT INTO books(id, title, author, price, quantity) VALUES(1001, "Effective Java", "Joshua Bloch", 959.5, 10);
INSERT INTO books(id, title, author, price, quantity) VALUES(1002, "Java Concurrency in Practice", "Brian Goetz", 569.0, 10);
INSERT INTO books(id, title, author, price, quantity) VALUES(1003, "Clean Code", "Robert Cecil Martin", 637.0, 10);
INSERT INTO books(id, title, author, price, quantity) VALUES(1004, "Cracking the coding interview", "Gayle Laakmann McDowell", 599.0, 10);
INSERT INTO books(id, title, author, price, quantity) VALUES(1005, "Data Structures and Algorithms Made Easy", "Narasimha Karumanchi", 449.0, 10);
INSERT INTO books(id, title, author, price, quantity) VALUES(1006, "Operating System Concepts", "Abraham Silberschatz, Greg Gagne, and Peter Baer Galvin", 1090.0, 10);
INSERT INTO books(id, title, author, price, quantity) VALUES(1007, "Compilers: Principles, Techniques, and Tools", "Alfred V Abo", 279.0, 10);
INSERT INTO books(id, title, author, price, quantity) VALUES(1008, "Design Patterns: Elements of Reusable Object-Oriented Software", "Gamma Erich, Helm Richard, Johnson Ralph, Vlissides John", 1139.0, 10);

-- Inserting into carts
INSERT INTO cart(id, user_id, book_id, quantity) VALUES(1, 102, 1003, 2);

select * from cart;
select * from users;
select * from books;

DELETE FROM books WHERE id=1003;

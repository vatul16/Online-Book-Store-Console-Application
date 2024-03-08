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
    price DECIMAL(10, 2)
);

-- Creating Cart Table
CREATE TABLE cart(
	id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    book_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);
alter table cart add quantity int;
alter table cart modify user_id int, add constraint uid FOREIGN KEY (user_id) REFERENCES users(id) on delete cascade;
alter table cart modify book_id int, add constraint bid FOREIGN KEY (book_id) REFERENCES books(id) on delete cascade;
select * from cart;
select * from users;
select * from books;

insert into users(username,password,isAdmin) values("root","root",true);

DROP Table CUSTOMER;
DROP TABLE BOOK;
DROP TABLE ORDERS;
CREATE TABLE CUSTOMER (id varchar(100) PRIMARY KEY, name varchar(100) NOT NULL, email_id varchar(100) NOT NULL,mobile_no varchar(10) NOT NULL);
CREATE TABLE BOOK (id varchar(100) PRIMARY KEY, name varchar(100) NOT NULL,author varchar(100) NOT NULL,quantity int NOT NULL, price number NOT NULL);
CREATE TABLE ORDERS(id varchar(100) PRIMARY KEY, customer_id varchar(100) NOT NULL, order_date Date NOT NULL, book_count int NOT NULL, price number NOT NULL, FOREIGN KEY (customer_id) REFERENCES CUSTOMER(id));

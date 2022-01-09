DROP Table CUSTOMER;
CREATE TABLE CUSTOMER (id varchar(50) PRIMARY KEY, name varchar(25), email_id varchar(25),mobile_no varchar(10));
DROP TABLE BOOK;
CREATE TABLE BOOK (id varchar(50) PRIMARY KEY, name varchar(20),author varchar(20),quantity int, price number);
DROP TABLE ORDERS;
CREATE TABLE ORDERS(id varchar(50) PRIMARY KEY, customer_id varchar(50), order_date Date, quantity int, price number, FOREIGN KEY (customer_id) REFERENCES CUSTOMER(id));

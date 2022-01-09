package com.assignment.readingisgood.services;

import com.assignment.readingisgood.exceptions.*;
import com.assignment.readingisgood.models.Book;
import com.assignment.readingisgood.models.BookQuantity;

import java.util.List;

public interface BookService {
    String addBook(Book book) throws BookAlreadyPresent, InvalidInput;
    BookQuantity updateQuantity(String id,Integer quantity) throws BookNotFound, OutOfStockException;
    List<BookQuantity> getAllStock();
    Integer getQuantity(String id) throws BookNotFound;
    Book getBook(String id) throws BookNotFound;
}

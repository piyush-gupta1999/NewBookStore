package com.assignment.readingisgood.services;

import com.assignment.readingisgood.exceptions.BookAlreadyPresent;
import com.assignment.readingisgood.exceptions.BookNotFound;
import com.assignment.readingisgood.exceptions.InvalidQuantity;
import com.assignment.readingisgood.exceptions.OutOfStockException;
import com.assignment.readingisgood.models.Book;
import com.assignment.readingisgood.models.BookQuantity;

import java.util.List;

public interface BookService {
    String addBook(Book book)  throws BookAlreadyPresent, InvalidQuantity;
    BookQuantity updateQuantity(String id,Integer quantity) throws InvalidQuantity, BookNotFound, OutOfStockException;
    List<BookQuantity> getAllStock();
    Integer getQuantity(String id) throws BookNotFound;
    Book getBook(String id) throws BookNotFound;
}

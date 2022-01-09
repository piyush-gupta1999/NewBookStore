package com.assignment.readingisgood.services;

import com.assignment.readingisgood.exceptions.*;
import com.assignment.readingisgood.models.Book;
import com.assignment.readingisgood.models.BookQuantity;
import com.assignment.readingisgood.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public String addBook(Book book) throws BookAlreadyPresent, InvalidInput {
         String id = book.getId();
         validateInputs(book);
         if(bookRepository.existsById(id)){
             throw new BookAlreadyPresent("Book is present with id:"+id);
         }else{
             bookRepository.save(book);
             return id;
         }
    }

    @Override
    public synchronized BookQuantity updateQuantity(String id, Integer quantity) throws OutOfStockException, BookNotFound {
        Book book = getBook(id);
        int updatedQuantity = bookRepository.getById(id).getQuantity() + quantity;
        if(updatedQuantity < 0){
            throw new OutOfStockException("Book with id: " + id+ " is out of stock.");
        }
        book.setQuantity(updatedQuantity);
        bookRepository.save(book);
        return new BookQuantity(book.getId(),updatedQuantity);
    }

    @Override
    public List<BookQuantity> getAllStock() {
        return bookRepository.findAll().stream().map(book -> new BookQuantity(book.getId(),book.getQuantity())).collect(Collectors.toList());
    }
    public Integer getQuantity(String bookId) throws BookNotFound {
        return getBook(bookId).getQuantity();
    }

    public Book getBook(String bookId) throws BookNotFound {
        if(bookRepository.existsById(bookId)){
            return bookRepository.getById(bookId);
        }else {
            throw new BookNotFound("Book Not Found with Id:"+bookId);
        }
    }
    private void validateInputs(Book book) throws InvalidInput{
        if(book.getId().equals("")){
            throw new InvalidInput("Book id can't be empty.");
        }else if(book.getName().equals("")){
            throw new InvalidInput("Book name can't be empty.");
        }else if(book.getAuthor().equals("")){
            throw new InvalidInput("Author name can't be empty.");
        }else if(book.getQuantity() <= 0){
            throw new InvalidInput("Quantity should be positive.");
        }else if(book.getPrice()<(double)0){
            throw new InvalidInput("Price can't be negative.");
        }
    }
}

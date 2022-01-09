package com.assignment.readingisgood.services;

import com.assignment.readingisgood.exceptions.BookAlreadyPresent;
import com.assignment.readingisgood.exceptions.BookNotFound;
import com.assignment.readingisgood.exceptions.InvalidQuantity;
import com.assignment.readingisgood.exceptions.OutOfStockException;
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
    public String addBook(Book book) throws InvalidQuantity, BookAlreadyPresent {
         String id = book.getId();
         Integer quantity = book.getQuantity();
         if(quantity<0){
            throw new InvalidQuantity("Quantity should be positive.");
         }else if(bookRepository.existsById(id)){
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
            throw new OutOfStockException("Can't update OOS.");
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
}

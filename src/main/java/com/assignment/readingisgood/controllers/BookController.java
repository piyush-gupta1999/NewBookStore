package com.assignment.readingisgood.controllers;

import com.assignment.readingisgood.exceptions.BookAlreadyPresent;
import com.assignment.readingisgood.exceptions.BookNotFound;
import com.assignment.readingisgood.exceptions.InvalidQuantity;
import com.assignment.readingisgood.exceptions.OutOfStockException;
import com.assignment.readingisgood.models.Book;
import com.assignment.readingisgood.models.BookQuantity;
import com.assignment.readingisgood.models.BookResponse;
import com.assignment.readingisgood.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books/update/{bookId}/quantity/{quantity}",method = RequestMethod.GET)
    @ResponseBody
    public BookResponse updateQuantity(@PathVariable String bookId, @PathVariable Integer quantity) {
        try{
            BookQuantity bookQuantity = bookService.updateQuantity(bookId,quantity);
            return new BookResponse("Success",bookQuantity);
        } catch (Exception exception) {
            return new BookResponse("Fail",exception.getMessage());
        }
    }

    @RequestMapping(value = "/books/add",method = RequestMethod.POST)
    @ResponseBody
    public BookResponse addBook(@RequestBody Book book) {
        try{
            return new BookResponse("Success",bookService.addBook(book));
        } catch (Exception exception) {
            return new BookResponse("Fail",exception.getMessage());
        }
    }

    @RequestMapping(value = "/books/{book_id}",method = RequestMethod.GET)
    @ResponseBody
    public BookResponse getBookById(@PathVariable String book_id) {
        try{
            Book book = bookService.getBook(book_id);
            return new BookResponse("Success",book.toString());
        } catch (Exception exception) {
            return new BookResponse("Fail",exception.getMessage());
        }
    }

    @RequestMapping(value = "/books/stock",method = RequestMethod.GET)
    @ResponseBody
    public BookResponse getAllStock() {
        try {
        return new BookResponse("Success", bookService.getAllStock());
        }catch (Exception exception) {
            return new BookResponse("Fail",exception.getMessage());
        }
    }
}

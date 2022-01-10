package com.assignment.readingisgood.controllers;

import com.assignment.readingisgood.exceptions.InvalidInput;
import com.assignment.readingisgood.models.Book;
import com.assignment.readingisgood.models.BookQuantity;
import com.assignment.readingisgood.models.BookResponse;
import com.assignment.readingisgood.models.Response;
import com.assignment.readingisgood.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books/update",method = RequestMethod.POST)
    @ResponseBody
    public Response updateQuantity(@RequestBody BookQuantity bookQuantity) {
        try{
            if(bookQuantity.getQuantity() < 0){
                throw new InvalidInput("Quantity should be positive.");
            }
            BookQuantity bookQuantity2 = bookService.updateQuantity(bookQuantity.getBookId(),bookQuantity.getQuantity());
            return new BookResponse("Success",bookQuantity2);
        } catch (Exception exception) {
            return new BookResponse("Fail",exception.getMessage());
        }
    }

    @RequestMapping(value = "/books/add",method = RequestMethod.POST)
    @ResponseBody
    public Response addBook(@RequestBody Book book) {
        try{
            return new BookResponse("Success",bookService.addBook(book));
        } catch (Exception exception) {
            return new BookResponse("Fail",exception.getMessage());
        }
    }

    @RequestMapping(value = "/books/{book_id}",method = RequestMethod.GET)
    @ResponseBody
    public Response getBookById(@PathVariable String book_id) {
        try{
            Book book = bookService.getBook(book_id);
            return new BookResponse("Success",book.toString());
        } catch (Exception exception) {
            return new BookResponse("Fail",exception.getMessage());
        }
    }

    @RequestMapping(value = "/books/stock",method = RequestMethod.GET)
    @ResponseBody
    public Response getAllStock() {
        try {
        return new BookResponse("Success", bookService.getAllStock());
        }catch (Exception exception) {
            return new BookResponse("Fail",exception.getMessage());
        }
    }
}

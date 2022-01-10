package com.assignment.readingisgood.controllers;

import com.assignment.readingisgood.exceptions.BookNotFound;
import com.assignment.readingisgood.models.Book;
import com.assignment.readingisgood.models.BookResponse;
import com.assignment.readingisgood.repository.BookRepository;
import com.assignment.readingisgood.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = BookController.class)
@WithMockUser
@ContextConfiguration(classes = {BookController.class})
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    Book mockBook = new Book("1", "Science","Piyush Gupta",100,250.22);
    BookResponse mockCustomerAPIResponse = new BookResponse("Success",mockBook.toString());
    Exception exception = new BookNotFound("Book Not Found with Id:2");
    @Test
    public void addNewBook_forbidden() throws Exception {
        Mockito.when(bookService.addBook(mockBook)).thenReturn(String.valueOf(mockBook));
        mockMvc.perform(MockMvcRequestBuilders.post("/books/add")
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockBook))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
    @Test
    public void getBookById_success() throws Exception{
        Mockito.when(bookService.getBook(mockBook.getId())).thenReturn(mockBook);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.status",is("Success")))
                .andExpect(jsonPath("$.description",is(mockBook.toString())));
    }

    @Test
    public void getBookById_fail() throws Exception{
        String bookId = "2";
        Mockito.when(bookService.getBook(bookId)).thenThrow(new BookNotFound("Book Not Found with Id:"+bookId));
        mockMvc.perform(MockMvcRequestBuilders.get("/books/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.status", is("Fail")))
                .andExpect(jsonPath("$.description", is(exception.getMessage())));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

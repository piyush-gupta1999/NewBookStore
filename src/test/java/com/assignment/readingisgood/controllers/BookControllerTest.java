package com.assignment.readingisgood.controllers;

import com.assignment.readingisgood.models.Book;
import com.assignment.readingisgood.models.BookResponse;
import com.assignment.readingisgood.services.BookService;
import com.assignment.readingisgood.services.CustomerServices;
import com.assignment.readingisgood.services.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CustomerController.class)
@WithMockUser
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private CustomerServices customerServices;

    @MockBean
    private OrderService orderService;



    Book mockBook = new Book("1", "Science","Piyush Gupta",100,250.22);
    BookResponse mockCustomerAPIResponse = new BookResponse("Success","1");
//    @Test
//    public void addNewBook() throws Exception {
//        Mockito.when(bookService.addBook(new Book(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyDouble()))).thenReturn("1");
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customers/add").accept(MediaType.APPLICATION_JSON).content(mockBook).contentType(MediaType.APPLICATION_JSON);
////        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
////        System.out.println("RESULT: " + result.getResponse().getContentAsString());
//        //Response expected = new CustomerResponse(200,"Success",id);
//        //String expected = "{status_code: 200,description: \"Success\",customer_id: \""+id+"\"}";
//        //JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),false);
//
//    }
    @Test
    public void getBookById_success() throws Exception{
        Mockito.when(bookService.getBook("1")).thenReturn(mockBook);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name",is("Science")));
    }


}

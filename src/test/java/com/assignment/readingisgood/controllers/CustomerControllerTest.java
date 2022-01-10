package com.assignment.readingisgood.controllers;

import com.assignment.readingisgood.exceptions.BookNotFound;
import com.assignment.readingisgood.exceptions.OrderNotFound;
import com.assignment.readingisgood.models.Customer;
import com.assignment.readingisgood.models.CustomerResponse;
import com.assignment.readingisgood.models.Order;
import com.assignment.readingisgood.models.Response;
import com.assignment.readingisgood.repository.CustomerRepository;
import com.assignment.readingisgood.repository.OrderRepository;
import com.assignment.readingisgood.services.CustomerServices;
import com.assignment.readingisgood.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CustomerController.class)
@WithMockUser
@ContextConfiguration(classes = {CustomerController.class})
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServices customerServices;

    @MockBean
    private OrderService orderService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private OrderRepository orderRepository;

    String id;
    Response mockCustomerAPIResponse;
    Customer mockCustomer;
    Date date;
    Exception exception;

    @Before
    public void setUp() {
         id = UUID.randomUUID().toString();
         mockCustomerAPIResponse = new CustomerResponse("Success",id);
         mockCustomer = new Customer("Piyush Gupta", "guptapiyush963@gmail.com","7015775512");
         date = new Date();
         mockCustomer.setId(id);
         Mockito.when(customerRepository.existsById(mockCustomer.getId()))
                .thenReturn(true);
    }

    @Test
    public void addNewCustomer_forbidden() throws Exception {
        Mockito.when(customerServices.addCustomer(mockCustomer)).thenReturn(String.valueOf(mockCustomerAPIResponse));
        mockMvc.perform(MockMvcRequestBuilders.post("/customers/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockCustomer)))
                .andExpect(status().isForbidden());
    }
    @Test
    public void getCustomerOrders_success() throws Exception{
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("1",id,date,100,1000.00));
        Mockito.when(orderService.getCustomerOrders(id)).thenReturn(orders);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/customers/"+id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.status",is("Success")));
    }

    @Test
    public void getCustomerOrdersEmpty() throws Exception{
        exception = new OrderNotFound("Order Not Found with Id:2");
        Mockito.when(orderService.getCustomerOrders(id)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/customers/"+id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("Success")))
                .andExpect(jsonPath("$.description", is(new ArrayList())));
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

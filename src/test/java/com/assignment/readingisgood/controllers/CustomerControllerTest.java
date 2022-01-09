package com.assignment.readingisgood.controllers;

import com.assignment.readingisgood.models.Customer;
import com.assignment.readingisgood.models.CustomerResponse;
import com.assignment.readingisgood.services.BookService;
import com.assignment.readingisgood.services.CustomerServices;
import com.assignment.readingisgood.services.OrderService;
import com.assignment.readingisgood.services.StatisticsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.UUID;

@WebMvcTest(value = CustomerController.class)
@WithMockUser
@EnableJpaRepositories
@ComponentScan("com.assignment.readingisgood")
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EntityManagerFactory entityManager;

    @MockBean
    private CustomerServices customerServices;

    @MockBean
    private BookService bookService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private StatisticsService statisticsService;

    String id = UUID.randomUUID().toString();
    Customer mockCustomer = new Customer("Piyush Gupta", "guptapiyush963@gmail.com","7015775512");
    CustomerResponse mockCustomerAPIResponse = new CustomerResponse("Success",id);
    @Test
    public void addNewCustomer_success() throws Exception {
        Mockito.when(customerServices.addCustomer(new Customer(Mockito.anyString(),Mockito.anyString(),Mockito.anyString()))).thenReturn(mockCustomerAPIResponse.toString());
        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                .post("/customers/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mockCustomer.toString())
                        .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println("RESULT: " + result.getResponse().getContentAsString());
        String expected = mockCustomerAPIResponse.toString();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),false);
    }
}

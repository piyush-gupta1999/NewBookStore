package com.assignment.readingisgood.controllers;

import com.assignment.readingisgood.models.Customer;
import com.assignment.readingisgood.models.CustomerResponse;
import com.assignment.readingisgood.models.Response;
import com.assignment.readingisgood.repository.CustomerRepository;
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
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.util.UUID;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
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

    String id = UUID.randomUUID().toString();
    Response mockCustomerAPIResponse = new CustomerResponse("Success",id);
    Customer mockCustomer = new Customer("Piyush Gupta", "guptapiyush963@gmail.com","7015775512");

    @Before
    public void setUp() {
        Customer mockCustomer = new Customer("Piyush Gupta", "guptapiyush963@gmail.com","7015775512");
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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

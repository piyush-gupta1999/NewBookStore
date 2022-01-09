package com.assignment.readingisgood.controllers;

import com.assignment.readingisgood.exceptions.UserAlreadyExist;
import com.assignment.readingisgood.models.BookResponse;
import com.assignment.readingisgood.models.Customer;
import com.assignment.readingisgood.models.CustomerResponse;
import com.assignment.readingisgood.services.CustomerServices;
import com.assignment.readingisgood.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/customers/add",method = RequestMethod.POST)
    @ResponseBody
    public CustomerResponse addCustomer(@RequestBody Customer customer) {
        CustomerResponse customerResponse;
        try{
            customerResponse = new CustomerResponse("Success",customerServices.addCustomer(customer));
        }catch (UserAlreadyExist userAlreadyExist) {
            customerResponse = new CustomerResponse("Fail",userAlreadyExist.getMessage());
        }
        return customerResponse;
    }

    @RequestMapping(value = "/customers/{customerId}",method = RequestMethod.GET)
    public CustomerResponse getCustomerOrders(@PathVariable("customerId") String customerId) {
        try{
            return new CustomerResponse("Success",orderService.getCustomerOrders(customerId));
        }catch (Exception exception) {
            return new CustomerResponse("Fail",exception.getMessage());
        }
    }
}

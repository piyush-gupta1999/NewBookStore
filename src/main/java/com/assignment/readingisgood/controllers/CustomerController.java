package com.assignment.readingisgood.controllers;

import com.assignment.readingisgood.models.Customer;
import com.assignment.readingisgood.models.CustomerResponse;
import com.assignment.readingisgood.models.Response;
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
    public Response addCustomer(@RequestBody Customer customer) {
        try{
            return new CustomerResponse("Success",customerServices.addCustomer(customer));
        }catch (Exception exception) {
            return new CustomerResponse("Fail",exception.getMessage());
        }
    }

    @RequestMapping(value = "/customers/{customerId}",method = RequestMethod.GET)
    public Response getCustomerOrders(@PathVariable("customerId") String customerId) {
        try{
            return new CustomerResponse("Success",orderService.getCustomerOrders(customerId));
        }catch (Exception exception) {
            return new CustomerResponse("Fail",exception.getMessage());
        }
    }
}

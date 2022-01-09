package com.assignment.readingisgood.services;

import com.assignment.readingisgood.exceptions.InvalidInput;
import com.assignment.readingisgood.exceptions.UserAlreadyExist;
import com.assignment.readingisgood.models.Customer;

public interface CustomerServices {
    String addCustomer(Customer customer) throws UserAlreadyExist, InvalidInput;
    boolean validateCustomer(String customerId);
}



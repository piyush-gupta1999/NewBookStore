package com.assignment.readingisgood.services;

import com.assignment.readingisgood.exceptions.UserAlreadyExist;
import com.assignment.readingisgood.models.Customer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

public interface CustomerServices {
    public String addCustomer(Customer customer) throws UserAlreadyExist;
    public boolean validateCustomer(String customerId);
}



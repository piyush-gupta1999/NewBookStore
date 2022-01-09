package com.assignment.readingisgood.services;

import com.assignment.readingisgood.exceptions.InvalidInput;
import com.assignment.readingisgood.exceptions.UserAlreadyExist;
import com.assignment.readingisgood.models.Customer;
import com.assignment.readingisgood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Component
public class CustomerServiceImpl implements CustomerServices{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String addCustomer(Customer customer) throws UserAlreadyExist, InvalidInput {
        validateInputs(customer);
        int val = customerRepository.getCustomerByEmailId(customer.getEmail_id());
        if(val > 0)
            throw new UserAlreadyExist("User already exist with Email Id:"+ customer.getEmail_id());
        customer.setId(UUID.randomUUID().toString());
        customerRepository.save(customer);
        return customer.getId();
    }

    @Override
    public boolean validateCustomer(String customerId) {
        return customerRepository.existsById(customerId);
    }

    private void validateInputs(Customer customer) throws InvalidInput {
        if(customer.getName().equals("")){
            throw new InvalidInput("Name can't be empty.");
        }else if(customer.getEmail_id().equals("")){
            throw new InvalidInput("Email id can't be empty.");
        }else if (customer.getMobile_no().equals("")){
            throw new InvalidInput("Mobile number can't be empty.");
        }
    }
}

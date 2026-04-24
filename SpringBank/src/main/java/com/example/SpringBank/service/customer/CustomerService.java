package com.example.SpringBank.service.customer;

import com.example.SpringBank.domain.model.Customer;
import com.example.SpringBank.dto.CustomerDto;
import com.example.SpringBank.exception.EntityNotFoundException;

import java.util.List;

/**
 * Service interface for Customer management in the FinVault system.
 */
public interface CustomerService {

    /**
     * Registers a new customer in the system.
     */
    Customer createCustomer(CustomerDto customerDto);

    /**
     * Retrieves a customer based on their unique SSN.
     */
    Customer getCustomer(String ssn) throws EntityNotFoundException;

    /**
     * Returns a list of all registered customers.
     */
    List<Customer> getAllCustomers();
}
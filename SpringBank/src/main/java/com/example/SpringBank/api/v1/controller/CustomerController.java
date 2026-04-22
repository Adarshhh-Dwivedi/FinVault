package com.example.SpringBank.api.v1.controller;

import com.example.SpringBank.api.v1.request.customer.CreateCustomerRequest;
import com.example.SpringBank.domain.model.Customer;
import com.example.SpringBank.dto.CustomerDto;
import com.example.SpringBank.exception.EntityException;
import com.example.SpringBank.exception.EntityNotFoundException;
import com.example.SpringBank.service.customer.CustomerService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Refactored for SpringBank project.
 */
@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper mapper;

    // Standard Constructor Injection for Spring Boot 3.x+
    public CustomerController(CustomerService customerService, ModelMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public List<CustomerDto> getAllCustomers() {
        // If list is null or empty, .stream() handles it safely if initialized properly in service
        return customerService.getAllCustomers()
                .stream()
                .map(customer -> mapper.map(customer, CustomerDto.class))
                .toList();
    }

    @GetMapping("/{ssn}")
    public CustomerDto getCustomer(@PathVariable("ssn") String ssn) throws EntityNotFoundException {
        Customer customer = customerService.getCustomer(ssn);
        // MapStruct or ModelMapper will handle the mapping
        return mapper.map(customer, CustomerDto.class);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED) // Better REST practice to return 201 Created
    public CustomerDto createCustomer(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) throws EntityException {
        CustomerDto customerDto = mapper.map(createCustomerRequest, CustomerDto.class);
        Customer customer = customerService.createCustomer(customerDto);
        return mapper.map(customer, CustomerDto.class);
    }
}
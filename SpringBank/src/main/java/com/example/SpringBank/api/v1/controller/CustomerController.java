package com.example.SpringBank.api.v1.controller;

import com.example.SpringBank.api.v1.request.customer.CreateCustomerRequest;
import com.example.SpringBank.domain.model.Customer;
import com.example.SpringBank.dto.CustomerDto;
import com.example.SpringBank.exception.EntityException;
import com.example.SpringBank.exception.EntityNotFoundException;
import com.example.SpringBank.service.customer.CustomerService;
import jakarta.validation.Valid; // UPDATED: Changed from javax to jakarta
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/")
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers != null && !customers.isEmpty()) {
            return customers.stream()
                    .map(customer -> mapper.map(customer, CustomerDto.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @GetMapping("/{ssn}")
    public CustomerDto getCustomer(@PathVariable("ssn") String ssn) throws EntityNotFoundException {
        Customer customer = customerService.getCustomer(ssn);
        if (customer != null) {
            return mapper.map(customer, CustomerDto.class);
        }
        return null;
    }

    @PostMapping("/create")
    public CustomerDto createCustomer(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) throws EntityException {
        CustomerDto customerDto = mapper.map(createCustomerRequest, CustomerDto.class);
        Customer customer = customerService.createCustomer(customerDto);
        if (customer != null) {
            return mapper.map(customer, CustomerDto.class);
        }
        return null;
    }
}
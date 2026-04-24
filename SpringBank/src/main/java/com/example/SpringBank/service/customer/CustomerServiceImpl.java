package com.bankofspring.service.customer; // Updated to match SpringBank project structure

import com.bankofspring.domain.model.Customer;
import com.bankofspring.domain.repository.CustomerRepository;
import com.bankofspring.dto.CustomerDto;
import com.bankofspring.exception.DuplicateEntityException;
import com.bankofspring.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of Customer management logic for SpringBank.
 * Built with modern Spring Boot standards and Java 21.
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(String ssn) {
        return Optional.ofNullable(customerRepository.findBySsn(ssn))
                .orElseThrow(() -> new EntityNotFoundException(Customer.class, "ssn", ssn));
    }

    @Override
    @Transactional
    public Customer createCustomer(CustomerDto customerDto) {
        if (customerRepository.findBySsn(customerDto.getSsn()) != null) {
            throw new DuplicateEntityException(Customer.class, "ssn", customerDto.getSsn());
        }

        Customer customer = mapper.map(customerDto, Customer.class);
        return customerRepository.save(customer);
    }
}
package com.task.quotation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.quotation.entity.Customer;
import com.task.quotation.repository.CustomerRepository;


@RestController
@RequestMapping("/api")
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    // Create a new customer
    @PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }
    
    // Get all customers
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    // Get a customer by id
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable(value = "id") Long customerId) {
        final Long id = customerId;
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("CustomerId %d not found", id)));
    }
    

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
                                                    @Valid @RequestBody Customer customerDetails) {
        Customer customer = customerRepository.findById(customerId)
                                               .orElseThrow(() -> new RuntimeException(String.format("Customer", "id", customerId)));

        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        customer.setMiddleName(customerDetails.getMiddleName());
        customer.setEmail(customerDetails.getEmail());
        customer.setBirthDate(customerDetails.getBirthDate());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
        Customer updatedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }
}

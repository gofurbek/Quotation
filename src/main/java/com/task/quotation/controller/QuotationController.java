package com.task.quotation.controller;

import com.task.quotation.entity.Customer;
import com.task.quotation.entity.Quotation;
import com.task.quotation.repository.CustomerRepository;
import com.task.quotation.repository.QuotationRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuotationController {

    @Autowired
    private QuotationRepository quotationRepository;
    
    @Autowired    
    private CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<Quotation> createQuotation(@RequestBody Quotation quotation) {
        Quotation createdQuotation = quotationRepository.save(quotation);
        return new ResponseEntity<Quotation>(createdQuotation, HttpStatus.CREATED);
    }

    @PutMapping("/quotations/{id}")
    public ResponseEntity<Quotation> updateQuotation(@PathVariable Long id, @RequestBody Quotation quotation) {
        Quotation existingQuotation = quotationRepository.findById(id).orElse(null);
        if (existingQuotation == null) {
            return new ResponseEntity<Quotation>(HttpStatus.NOT_FOUND);
        }
        if (quotation.equals(existingQuotation)) {
            // If the new quotation is identical to the existing one, return the existing one
            return new ResponseEntity<Quotation>(existingQuotation, HttpStatus.OK);
        }
        existingQuotation.setBeginingOfInsurance(quotation.getBeginingOfInsurance());
        existingQuotation.setInsuredAmount(quotation.getInsuredAmount());
        existingQuotation.setDateOfSigningMortgage(quotation.getDateOfSigningMortgage());
        existingQuotation.setCustomer(quotation.getCustomer());
        Quotation updatedQuotation = quotationRepository.save(existingQuotation);
        return new ResponseEntity<Quotation>(updatedQuotation, HttpStatus.OK);
    }
    
    
    
    @GetMapping("/customers/{id}/quotations")
    public List<Quotation> getQuotationsByCustomerId(@PathVariable Long id) {
        return quotationRepository.findByCustomerId(id);
    }
    
    @PostMapping("/customers/{id}/quotations")
    public ResponseEntity<Quotation> addQuotation(@PathVariable Long id, @RequestBody Quotation quotation) {
		Customer customer = customerRepository.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<Quotation>(HttpStatus.NOT_FOUND);
        }
        quotation.setCustomer(customer);
        Quotation createdQuotation = quotationRepository.save(quotation);
        return new ResponseEntity<Quotation>(createdQuotation, HttpStatus.CREATED);
    }  
}

package com.task.quotation.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.quotation.entity.Customer;
import com.task.quotation.entity.Quotation;
import com.task.quotation.entity.Subscription;
import com.task.quotation.repository.QuotationRepository;
import com.task.quotation.repository.SubscriptionRepository;

@RestController
@RequestMapping("/api")
public class SubscriptionController {
    
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    
    @Autowired
    private QuotationRepository quotationRepository;

    // Create a new subscription
    @PostMapping("/subscriptions")
    public Subscription createSubscription(@Valid @RequestBody Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }
    @PostMapping("/quotations/{id}/subscriptions")
    public ResponseEntity<Subscription> addSubscription(@PathVariable Long id, @RequestBody Subscription subscription) {
		Quotation quotation = quotationRepository.findById(id).orElse(null);
        if (quotation == null) {
            return new ResponseEntity<Subscription>(HttpStatus.NOT_FOUND);
        }
        subscription.setQuotation(quotation);
        Subscription createdsubscription = subscriptionRepository.save(subscription);
        return new ResponseEntity<Subscription>(createdsubscription, HttpStatus.CREATED);
    }  
    
    
    // Get all subscriptions
    @GetMapping("/subscriptions")
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }
    
    // Get a subscription by id
    @GetMapping("/subscriptions/{id}")
    public Subscription getSubscriptionById(@PathVariable(value = "id") Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription"));
    }
    
    // Update a subscription by id
    @PutMapping("/subscriptions/{id}")
    public Subscription updateSubscription(@PathVariable(value = "id") Long subscriptionId,
                                           @Valid @RequestBody Subscription subscriptionDetails) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription"));
        
        subscription.setQuotation(subscriptionDetails.getQuotation());
        
        return subscriptionRepository.save(subscription);
    }
    
    // Delete a subscription by id
    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable(value = "id") Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription"));
        
        subscriptionRepository.delete(subscription);
        
        return ResponseEntity.ok().build();
    }
}

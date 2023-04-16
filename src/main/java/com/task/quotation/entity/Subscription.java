package com.task.quotation.entity;

import java.time.LocalDate;

import javax.persistence.*;
@Entity
@Table(name = "Subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate startDate;
    
    @Column(nullable = false)
    private LocalDate validUntil;
    
    @ManyToOne(optional = false)
    private Quotation quotation;
    
    // Getters
    
    public Long getId() {
        return id;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getValidUntil() {
        return validUntil;
    }
    
    public Quotation getQuotation() {
        return quotation;
    }
    
    // Setters
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }
    
    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }
}

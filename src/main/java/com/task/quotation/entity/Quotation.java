package com.task.quotation.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Quotation")
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate beginingOfInsurance;

    @Column(nullable = false)
    private BigDecimal insuredAmount;

    @Column(nullable = false)
    private LocalDate dateOfSigningMortgage;

    @ManyToOne(optional = false)
    private Customer customer;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBeginingOfInsurance() {
        return beginingOfInsurance;
    }

    public void setBeginingOfInsurance(LocalDate beginingOfInsurance) {
        this.beginingOfInsurance = beginingOfInsurance;
    }

    public BigDecimal getInsuredAmount() {
        return insuredAmount;
    }

    public void setInsuredAmount(BigDecimal insuredAmount) {
        this.insuredAmount = insuredAmount;
    }

    public LocalDate getDateOfSigningMortgage() {
        return dateOfSigningMortgage;
    }

    public void setDateOfSigningMortgage(LocalDate dateOfSigningMortgage) {
        this.dateOfSigningMortgage = dateOfSigningMortgage;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

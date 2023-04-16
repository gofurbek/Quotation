package com.task.quotation.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.task.quotation.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer getCustomerById(Long Id);	

    @Modifying
    @Query("UPDATE Customer c SET c.firstName = :firstName, c.lastName = :lastName, c.middleName = :middleName, c.email = :email, c.phoneNumber = :phoneNumber, c.birthDate = :birthDate WHERE c.id = :id")
    int updateCustomer(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("middleName") String middleName, @Param("email") String email, @Param("phoneNumber") String phoneNumber, @Param("birthDate") LocalDate birthDate);
}

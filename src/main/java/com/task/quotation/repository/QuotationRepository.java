package com.task.quotation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.task.quotation.entity.Quotation;
import java.util.List;


@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> {
    // Add any custom query methods here, if needed
    List<Quotation> findByCustomerId(Long customerId);
}

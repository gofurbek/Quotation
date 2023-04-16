package com.task.quotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.task.quotation.entity")
@EnableJpaRepositories("com.task.quotation.repository")
public class QuotationApplication {

    public static void main(String[] args) {
		SpringApplication.run(QuotationApplication.class, args);    	
    }
    
}

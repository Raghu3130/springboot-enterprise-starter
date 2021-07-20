package com.accio.spring.starter.repos;

import com.accio.spring.starter.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}

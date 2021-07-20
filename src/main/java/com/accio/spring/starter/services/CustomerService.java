package com.accio.spring.starter.services;

import com.accio.spring.starter.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer create(Customer toCreate) throws Exception;

    List<Customer> findAll();

    Optional<Customer> findById(String id);

    Boolean update(String id, Customer toUpdate) throws Exception;

    Boolean delete(String id);

}

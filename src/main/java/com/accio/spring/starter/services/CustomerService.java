package com.accio.spring.starter.services;

import com.accio.spring.starter.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer create(Customer toCreate) throws RuntimeException;

    List<Customer> findAll();

    Customer findById(String id) throws RuntimeException;

    Optional<Customer> findByIdNoException(String id);

    Boolean update(String id, Customer toUpdate) throws RuntimeException;

    Boolean delete(String id);

}

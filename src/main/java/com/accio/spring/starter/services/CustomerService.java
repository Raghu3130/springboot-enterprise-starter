package com.accio.spring.starter.services;

import com.accio.spring.starter.exceptions.BadRequestException;
import com.accio.spring.starter.exceptions.BaseRuntimeException;
import com.accio.spring.starter.exceptions.NotFoundException;
import com.accio.spring.starter.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer create(Customer toCreate) throws BadRequestException;

    List<Customer> findAll();

    Customer findById(String id) throws NotFoundException;

    Optional<Customer> findByIdNoException(String id);

    Boolean update(String id, Customer toUpdate) throws NotFoundException;

    Boolean delete(String id);

}

package com.accio.spring.starter.services;

import com.accio.spring.starter.exceptions.BadRequestException;
import com.accio.spring.starter.exceptions.NotFoundException;
import com.accio.spring.starter.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    /**
     * To create new customer in database.
     *
     * @param toCreate The customer to create
     * @return Customer created object
     * @throws BadRequestException any property will invalid it will throw
     */
    Customer create(Customer toCreate) throws BadRequestException;

    /**
     * To get all customers from database.
     *
     * @return List<Customer>
     */
    List<Customer> findAll();

    /**
     * To find a customer by id and throw Exception if not found.
     *
     * @param id The customer id as String
     * @return Customer object
     * @throws NotFoundException If customer not found it will throw exception
     */
    Customer findById(String id) throws NotFoundException;

    /**
     * To find a customer by id, it will not throw an Exception if not found.
     *
     * @param id The customer id as String
     * @return Optional<Customer>
     */
    Optional<Customer> findByIdNoException(String id);

    /**
     * To update customer in database.
     *
     * @param id       Customer id as String
     * @param toUpdate All properties of customer to update
     * @return Boolean (Returns true if customer update successfully)
     * @throws NotFoundException When customer not found by id
     */
    Boolean update(String id, Customer toUpdate) throws NotFoundException;

    /**
     * To delete customer from database.
     *
     * @param id Customer id as String
     * @return Boolean (Returns true in both condition customer not found and
     * deleted successfully
     */
    Boolean delete(String id);

}

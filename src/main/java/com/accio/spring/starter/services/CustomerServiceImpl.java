package com.accio.spring.starter.services;

import com.accio.spring.starter.exceptions.internal.InvalidDataException;
import com.accio.spring.starter.exceptions.internal.InvalidEmailException;
import com.accio.spring.starter.exceptions.internal.NotFoundException;
import com.accio.spring.starter.exceptions.internal.SomethingWrongException;
import com.accio.spring.starter.models.Customer;
import com.accio.spring.starter.repos.CustomerRepository;
import com.accio.spring.starter.utils.CopyObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository repository;

    @Autowired
    CopyObjectUtil copyObjectUtil;

    @Override
    public Customer create(Customer toCreate) throws Exception {
        try {
            // validate object
            // the validate method will throw an exception if not valid
            // thus no need to check isValid in if condition
            Boolean isValid = this.validate(toCreate);

            toCreate.setId(UUID.randomUUID().toString());
            repository.save(toCreate);
            return toCreate;
        } catch (Exception e) {
            // Log details here
            System.out.println("create customer error");
            // Throw back to handle it in integration layer
            throw e;
        }
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Customer> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Boolean update(String id, Customer toUpdate) throws Exception {
        try {
            Optional<Customer> optionalCustomer = this.findById(id);

            if (optionalCustomer.isEmpty()) {
                // not found
                throw new NotFoundException("Customer by id " + id + " not found");
            }

            Customer toSave = optionalCustomer.get();
            this.copyObjectUtil.copyNonNullProperties(toUpdate, toSave);
            this.repository.save(toSave);

            return true;
        } catch(Exception e) {
            // Log details here
            System.out.println("ERROR: while updating customer " + e);
            throw new SomethingWrongException("Something went wrong while updating customer");
        }
    }

    @Override
    public Boolean delete(String id) {
        repository.deleteById(id);
        return true;
    }

    private Boolean validate(Customer toValidate) throws Exception {
        if (toValidate.getEmail() == null || toValidate.getEmail().equals("")) {
            // Invalid email
            throw new InvalidEmailException("Invalid email");
        }

        if (toValidate.getFirstName() == null || toValidate.getFirstName().equals("")) {
            // Invalid data
            throw new InvalidDataException("Invalid first name");
        }

        if (toValidate.getLastName() == null || toValidate.getLastName().equals("")) {
            // Invalid data
            throw new InvalidDataException("Invalid last name");
        }
        return true;
    }



}

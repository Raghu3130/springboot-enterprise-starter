package com.accio.spring.starter.services;

import com.accio.spring.starter.exceptions.*;
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
    public Customer create(Customer toCreate) throws BadRequestException {
            // validate object
            // the validate method will throw an exception if not valid
            // thus no need to check isValid in if condition
            Boolean isValid = this.validate(toCreate);

            toCreate.setId(UUID.randomUUID().toString());
            repository.save(toCreate);
            return toCreate;
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Customer> findByIdNoException(String id) {
        return repository.findById(id);
    }

    @Override
    public Customer findById(String id) throws NotFoundException {
        Optional<Customer> optionalCustomer = repository.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException("Could not found customer with id " + id);
        }
        return optionalCustomer.get();
    }

    @Override
    public Boolean update(String id, Customer toUpdate) throws NotFoundException {
        try {
            Optional<Customer> optionalCustomer = this.findByIdNoException(id);

            if (optionalCustomer.isEmpty()) {
                // not found
                throw new NotFoundException("Could not found customer with id " + id);
            }

            Customer toSave = optionalCustomer.get();
            this.copyObjectUtil.copyNonNullProperties(toUpdate, toSave);
            this.repository.save(toSave);

            return true;
        }
        catch (NotFoundException e) {
            // Log details here
            throw e;
        }
        catch (Exception e) {
            // Log details here
            System.out.println("ERROR: while updating customer " + e);
            throw new InternalErrorException("Something went wrong while updating customer", e);
        }
    }

    @Override
    public Boolean delete(String id) {
        repository.deleteById(id);
        return true;
    }

    private Boolean validate(Customer toValidate) throws BadRequestException {
        String message = "";
        String prefix = "Invalid data in fields";
        if (toValidate.getEmail() == null || toValidate.getEmail().equals("")) {
            // Invalid email
            message += "email";
        }

        if (toValidate.getFirstName() == null || toValidate.getFirstName().equals("")) {
            // Invalid data
            if(message.isEmpty() == false) {
                message += ", firstName";
            } else {
                message += "firstName";
            }
        }

        if (toValidate.getLastName() == null || toValidate.getLastName().equals("")) {
            // Invalid data
            if(message.isEmpty() == false) {
                message += ", lastName";
            } else {
                message += "lastName";
            }
        }

        if (message.isBlank() || message.isEmpty()) {
            return true;
        }

        throw new BadRequestException(prefix + " " + message, ErrorCodes.CUSTOMER_INVALID_DATA_EXCEPTION.getCode());

    }


}

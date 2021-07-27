package com.accio.spring.starter.services;

import com.accio.spring.starter.exceptions.BadRequestException;
import com.accio.spring.starter.exceptions.EntityNotFoundException;
import com.accio.spring.starter.exceptions.InternalServerErrorException;
import com.accio.spring.starter.exceptions.customer.CustomerInvalidDataException;
import com.accio.spring.starter.exceptions.customer.CustomerInvalidEmailException;
import com.accio.spring.starter.exceptions.customer.CustomerNotFoundException;
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
    public Customer create(Customer toCreate) throws RuntimeException {
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
    public Optional<Customer> findByIdNoException(String id) {
        return repository.findById(id);
    }

    @Override
    public Customer findById(String id) {
        Optional<Customer> optionalCustomer = repository.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }
        return optionalCustomer.get();
    }

    @Override
    public Boolean update(String id, Customer toUpdate) throws CustomerNotFoundException, InternalServerErrorException {
        try {
            Optional<Customer> optionalCustomer = this.findByIdNoException(id);

            if (optionalCustomer.isEmpty()) {
                // not found
                throw new CustomerNotFoundException(id);
            }

            Customer toSave = optionalCustomer.get();
            this.copyObjectUtil.copyNonNullProperties(toUpdate, toSave);
            this.repository.save(toSave);

            return true;
        }
        catch (CustomerNotFoundException e) {
            // Log details here
            throw e;
        }
        catch (Exception e) {
            // Log details here
            System.out.println("ERROR: while updating customer " + e);
            throw new InternalServerErrorException(Customer.class, "Something went wrong while updating customer");
        }
    }

    @Override
    public Boolean delete(String id) {
        repository.deleteById(id);
        return true;
    }

    private Boolean validate(Customer toValidate) throws RuntimeException {
        if (toValidate.getEmail() == null || toValidate.getEmail().equals("")) {
            // Invalid email
            throw new CustomerInvalidEmailException(toValidate.getEmail());
        }

        if (toValidate.getFirstName() == null || toValidate.getFirstName().equals("")) {
            // Invalid data
            throw new CustomerInvalidDataException("firstName", toValidate.getFirstName());
        }

        if (toValidate.getLastName() == null || toValidate.getLastName().equals("")) {
            // Invalid data
            throw new CustomerInvalidDataException("lastName", toValidate.getLastName());
        }
        return true;
    }


}

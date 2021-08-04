package com.accio.spring.starter.services;

import com.accio.spring.starter.exceptions.BadRequestException;
import com.accio.spring.starter.exceptions.ErrorCodes;
import com.accio.spring.starter.exceptions.InternalErrorException;
import com.accio.spring.starter.exceptions.NotFoundException;
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
    /**
     * CustomerRepository instance.
     */
    @Autowired
    private CustomerRepository repository;

    /**
     * CopyObjectUtil instance.
     */
    @Autowired
    private CopyObjectUtil copyObjectUtil;

    /**
     * To create customer in database.
     *
     * @param toCreate Customer instance
     * @return Customer
     * @throws BadRequestException BadRequestException instance
     */
    @Override
    public Customer create(final Customer toCreate) throws BadRequestException {
        // validate object
        // the validate method will throw an exception if not valid
        // thus no need to check isValid in if condition
        this.validate(toCreate);

        toCreate.setId(UUID.randomUUID().toString());
        repository.save(toCreate);
        return toCreate;
    }

    /**
     * To get all customers from database.
     *
     * @return List<Customer>
     */
    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    /**
     * To find a customer by id, it will not throw an Exception if not found.
     *
     * @param id Customer id as String
     * @return Optional<Customer>
     */
    @Override
    public Optional<Customer> findByIdNoException(final String id) {
        return repository.findById(id);
    }

    /**
     * To find a customer by id and throw Exception if not found.
     *
     * @param id Customer id as String
     * @return Customer object
     * @throws NotFoundException The NotFoundException instance
     */
    @Override
    public Customer findById(final String id) throws NotFoundException {
        Optional<Customer> optionalCustomer = repository.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException(
                    "Could not found customer with id " + id
            );
        }
        return optionalCustomer.get();
    }

    /**
     * To update customer in database.
     *
     * @param id       Customer id as String
     * @param toUpdate Customer instance with properties need to update
     * @return Boolean (Returns true if customer update successfully)
     * @throws NotFoundException the NotFoundException instance
     */
    @Override
    public Boolean update(final String id, final Customer toUpdate)
            throws NotFoundException {
        try {
            Optional<Customer> optionalCustomer = this.findByIdNoException(id);

            if (optionalCustomer.isEmpty()) {
                // not found
                throw new NotFoundException(
                        "Could not found customer with id " + id
                );
            }

            Customer toSave = optionalCustomer.get();
            this.copyObjectUtil.copyNonNullProperties(toUpdate, toSave);
            this.repository.save(toSave);

            return true;
        } catch (NotFoundException e) {
            // Log details here
            throw e;
        } catch (Exception e) {
            // Log details here
            System.out.println("ERROR: while updating customer " + e);
            throw new InternalErrorException(
                    "Something went wrong while updating customer", e
            );
        }
    }

    /**
     * To delete customer from database.
     *
     * @param id Customer id as String
     * @return Boolean (Returns true in both condition customer not found and
     * deleted successfully
     */
    @Override
    public Boolean delete(final String id) {
        repository.deleteById(id);
        return true;
    }

    private Boolean validate(final Customer toValidate)
            throws BadRequestException {
        String message = "";
        String prefix = "Invalid data in fields";
        if (toValidate.getEmail() == null || toValidate.getEmail().equals("")) {
            // Invalid email
            message += "email";
        }

        if (
                toValidate.getFirstName() == null
                        || toValidate.getFirstName().equals("")
        ) {
            // Invalid data
            if (!message.isEmpty()) {
                message += ", firstName";
            } else {
                message += "firstName";
            }
        }

        if (
                toValidate.getLastName() == null
                        || toValidate.getLastName().equals("")
        ) {
            // Invalid data
            if (!message.isEmpty()) {
                message += ", lastName";
            } else {
                message += "lastName";
            }
        }

        if (message.isBlank() || message.isEmpty()) {
            return true;
        }

        throw new BadRequestException(
                prefix + " " + message,
                ErrorCodes.CUSTOMER_INVALID_DATA_EXCEPTION.getCode()
        );

    }

}

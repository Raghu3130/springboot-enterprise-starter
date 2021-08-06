package com.accio.spring.starter.controllers;

import com.accio.spring.starter.exceptions.BadRequestException;
import com.accio.spring.starter.exceptions.InternalErrorException;
import com.accio.spring.starter.exceptions.NotFoundException;
import com.accio.spring.starter.models.Customer;
import com.accio.spring.starter.responses.StandardResponse;
import com.accio.spring.starter.responses.StandardResponseBuilder;
import com.accio.spring.starter.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    /**
     * StandardResponseBuilder to build standard responses for client.
     */
    @Autowired
    private StandardResponseBuilder standardResponseBuilder;

    /**
     * CustomerService instance.
     */
    @Autowired
    private CustomerService service;

    /**
     * To get all customers.
     *
     * @return StandardResponse<List < Customer>>
     */
    @GetMapping("/api/customers")
    public ResponseEntity<StandardResponse<List<Customer>>> getAll() {
        try {

            List<Customer> allCustomers = this.service.findAll();
            StandardResponse<List<Customer>> standardResponse =
                    standardResponseBuilder.createSuccessResponse(
                            allCustomers,
                            HttpStatus.OK.getReasonPhrase(),
                            HttpStatus.OK.value(),
                            ""
                    );
            return new ResponseEntity<>(standardResponse, HttpStatus.OK);

        } catch (Exception e) {
            throw new InternalErrorException(
                    "Something went wrong while creating customer",
                    e
            );
        }
    }

    /**
     * To get a customer by id.
     *
     * @param id String
     * @return StandardResponse<Customer>
     * @throws NotFoundException when customer not found by id
     */
    @GetMapping("/api/customers/{id}")
    public ResponseEntity<StandardResponse<Customer>> getOne(
            @PathVariable final String id
    ) throws NotFoundException {

        Customer customer = this.service.findById(id);
        StandardResponse<Customer> standardResponse =
                standardResponseBuilder.createSuccessResponse(
                        customer,
                        HttpStatus.OK.getReasonPhrase(),
                        HttpStatus.OK.value(),
                        ""
                );
        return new ResponseEntity<>(standardResponse, HttpStatus.OK);

    }

    /**
     * To create a customer.
     * pass customer details in request body
     *
     * @param toCreate Customer
     * @return StandardResponse<Customer>
     * @throws BadRequestException When some values are invalid
     */
    @PostMapping("/api/customers")
    public ResponseEntity<StandardResponse<Customer>> create(
            @RequestBody final Customer toCreate
    ) throws BadRequestException {
        Customer customer = this.service.create(toCreate);
        StandardResponse<Customer> standardResponse =
                standardResponseBuilder.createSuccessResponse(
                        customer,
                        HttpStatus.OK.getReasonPhrase(),
                        HttpStatus.OK.value(),
                        "Customer created"
                );
        return new ResponseEntity<>(standardResponse, HttpStatus.OK);
    }

    /**
     * To update customer.
     * pass customer info to update along with customer id in request body.
     *
     * @param toUpdate Customer instance with properties need to update
     * @return StandardResponse<Boolean>
     * @throws NotFoundException When customer not found
     */
    @PutMapping("/api/customers")
    public ResponseEntity<StandardResponse<Boolean>> update(
            @RequestBody final Customer toUpdate
    ) throws NotFoundException {

        if (toUpdate.getId() == null || toUpdate.getId().isEmpty()) {
            throw new NotFoundException("Could not found route");
        }
        Boolean isUpdate = this.service.update(toUpdate.getId(), toUpdate);
        StandardResponse<Boolean> standardResponse =
                standardResponseBuilder.createSuccessResponse(
                        isUpdate,
                        HttpStatus.OK.getReasonPhrase(),
                        HttpStatus.OK.value(),
                        "Customer updated"
                );
        return new ResponseEntity<>(standardResponse, HttpStatus.OK);

    }

    /**
     * To delete a customer.
     *
     * @param id String
     * @return StandardResponse<Boolean>
     * @throws NotFoundException When customer not found
     */
    @DeleteMapping("/api/customers/{id}")
    public ResponseEntity<StandardResponse<Boolean>> update(
            @PathVariable final String id
    ) throws NotFoundException {

        if (id == null || id.equals("")) {
            throw new NotFoundException("Could not found route");
        }
        Boolean isDeleted = this.service.delete(id);
        StandardResponse<Boolean> standardResponse =
                standardResponseBuilder.createSuccessResponse(
                        isDeleted,
                        HttpStatus.OK.getReasonPhrase(),
                        HttpStatus.OK.value(),
                        "Customer deleted"
                );
        return new ResponseEntity<>(standardResponse, HttpStatus.OK);

    }

}

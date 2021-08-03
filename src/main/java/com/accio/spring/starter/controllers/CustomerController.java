package com.accio.spring.starter.controllers;

import com.accio.spring.starter.exceptions.BadRequestException;
import com.accio.spring.starter.exceptions.InternalErrorException;
import com.accio.spring.starter.exceptions.NotFoundException;
import com.accio.spring.starter.models.Customer;
import com.accio.spring.starter.responses.StandardResponse;
import com.accio.spring.starter.responses.StandardResponseBuilder;
import com.accio.spring.starter.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    StandardResponseBuilder standardResponseBuilder;

    @Autowired
    CustomerService service;

    @GetMapping("/api/customers")
    public ResponseEntity<StandardResponse<List<Customer>>> getAll() {
        try {

            List<Customer> allCustomers = this.service.findAll();
            StandardResponse<List<Customer>> standardResponse = standardResponseBuilder.createSuccessResponse(
                    allCustomers,
                    HttpStatus.OK.getReasonPhrase(),
                    HttpStatus.OK.value(),
                    ""
            );
            return new ResponseEntity<>(standardResponse, HttpStatus.OK);

        } catch (Exception e) {
            throw new InternalErrorException("Something went wrong while creating customer", e);
        }
    }

    @GetMapping("/api/customers/{id}")
    public ResponseEntity<StandardResponse<Customer>> getOne(@PathVariable String id) throws NotFoundException {

        Customer customer = this.service.findById(id);
        StandardResponse<Customer> standardResponse = standardResponseBuilder.createSuccessResponse(
                customer,
                HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
                ""
        );
        return new ResponseEntity<>(standardResponse, HttpStatus.OK);

    }

    @PostMapping("/api/customers")
    public ResponseEntity<StandardResponse<Customer>> create(@RequestBody Customer toCreate) throws BadRequestException {
        Customer customer = this.service.create(toCreate);
        StandardResponse<Customer> standardResponse = standardResponseBuilder.createSuccessResponse(
                customer,
                HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
                "Customer created"
        );
        return new ResponseEntity<>(standardResponse, HttpStatus.OK);
    }

    @PutMapping("/api/customers")
    public ResponseEntity<StandardResponse<Boolean>> update(@RequestBody Customer toUpdate) throws NotFoundException {

        if (toUpdate.getId() == null || toUpdate.getId().isEmpty()) {
            throw new NotFoundException("Could not found route");
        }
        Boolean isUpdate = this.service.update(toUpdate.getId(), toUpdate);
        StandardResponse<Boolean> standardResponse = standardResponseBuilder.createSuccessResponse(
                isUpdate,
                HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
                "Customer updated"
        );
        return new ResponseEntity<>(standardResponse, HttpStatus.OK);

    }

    @DeleteMapping("/api/customers/{id}")
    public ResponseEntity<StandardResponse<Boolean>> update(@PathVariable String id) throws NotFoundException {

        if (id == null || id.equals("")) {
            throw new NotFoundException("Could not found route");
        }
        Boolean isDeleted = this.service.delete(id);
        StandardResponse<Boolean> standardResponse = standardResponseBuilder.createSuccessResponse(
                isDeleted,
                HttpStatus.OK.getReasonPhrase(),
                HttpStatus.OK.value(),
                "Customer deleted"
        );
        return new ResponseEntity<>(standardResponse, HttpStatus.OK);


    }

}

package com.accio.spring.starter.controllers;

import com.accio.spring.starter.models.Customer;
import com.accio.spring.starter.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping("/api/customers")
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> allCustomers = this.service.findAll();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @GetMapping("/api/customers/{id}")
    public ResponseEntity<Customer> getOne(@PathVariable String id) throws Exception {
        Customer customer = this.service.findById(id);
        /*if (optionalCustomer.isEmpty()) {
            // return not found error
            // FIXME: throw an http error instead of returning response
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new CustomerNotFoundException(id);
        }*/
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}

package com.accio.spring.starter.controllers;

import com.accio.spring.starter.exceptions.NotFoundException;
import com.accio.spring.starter.models.Customer;
import com.accio.spring.starter.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/api/customers")
    public ResponseEntity<Customer> create(@RequestBody Customer toCreate) throws Exception {
        Customer customer = this.service.create(toCreate);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/api/customers")
    public ResponseEntity<Boolean> update(@RequestBody Customer toUpdate) throws Exception {
        if(toUpdate.getId() == null || toUpdate.getId().isEmpty()) {
            throw new NotFoundException();
        }
        Boolean isUpdate = this.service.update(toUpdate.getId(), toUpdate);
        return new ResponseEntity<>(isUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/api/customers/{id}")
    public ResponseEntity<Boolean> update(@PathVariable String id) throws Exception {
        if(id == null || id.equals("")) {
            throw new NotFoundException();
        }
        Boolean isUpdate = this.service.delete(id);
        return new ResponseEntity<>(isUpdate, HttpStatus.OK);
    }

}

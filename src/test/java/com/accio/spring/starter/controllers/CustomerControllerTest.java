package com.accio.spring.starter.controllers;


import com.accio.spring.starter.models.Customer;
import com.accio.spring.starter.services.CustomerService;
import com.accio.spring.starter.testhelpers.CustomerHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void createCustomerTest() throws Exception {
        Customer customer = CustomerHelper.createMockCustomerWithId();

        when(customerService.create(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                .content(new ObjectMapper().writeValueAsString(customer))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("johndoe@abc.com"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllCustomerTest() throws Exception {

        Customer customer = CustomerHelper.createMockCustomerWithId();

        List<Customer> customerList = new ArrayList<Customer>();
        customerList.add(customer);
        when(customerService.findAll()).thenReturn(customerList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(customer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("johndoe@abc.com"))
                .andExpect(status().isOk());
    }

    @Test
    public void findSingleCustomerTest() throws Exception {
        Customer customer = CustomerHelper.createMockCustomerWithId();
//        Optional<Customer> optionalCustomer = Optional.of(customer);
        when(customerService.findById(anyString())).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/" + customer.getId()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("johndoe@abc.com"))
                .andExpect(status().isOk());

    }

    /*
        Implement here other route tests
        e.g. update and delete
        and don't forget to check error cases and messages
     */

}

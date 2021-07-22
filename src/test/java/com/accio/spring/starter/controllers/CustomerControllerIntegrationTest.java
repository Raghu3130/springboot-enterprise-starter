package com.accio.spring.starter.controllers;

import com.accio.spring.starter.models.Customer;
import com.accio.spring.starter.testhelpers.CustomerHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    private Customer customer;

    CustomerControllerIntegrationTest() {
        this.customer = CustomerHelper.createMockCustomer();
    }


    private ResponseEntity<Customer> createCustomer() {
        HttpEntity<Customer> request = new HttpEntity<>(customer);
        ResponseEntity<Customer> createResponse = testRestTemplate.postForEntity("/api/customers", request, Customer.class);
        return createResponse;
    }


    @Test
    public void createCustomerTest() {
        HttpEntity<Customer> request = new HttpEntity<>(customer);

        ResponseEntity<Customer> response = testRestTemplate.postForEntity("/api/customers", request, Customer.class);

        assertNotNull(response.getBody().getId());
        customer.setId(response.getBody().getId());
        assertEquals("John", response.getBody().getFirstName());
        assertEquals("Doe", response.getBody().getLastName());
        assertEquals("johndoe@abc.com", response.getBody().getEmail());
    }


    @Test
    public void getSingleCustomerTest() {
        // creating new customer
        ResponseEntity<Customer> createResponse = createCustomer();

        String customerId = createResponse.getBody().getId();
        System.out.println("customer id is " + customerId);
        ResponseEntity<Customer> response = testRestTemplate.getForEntity("/api/customers/" + customerId, Customer.class);

        assertNotNull(response.getBody().getId());
        assertEquals("John", response.getBody().getFirstName());
        assertEquals("Doe", response.getBody().getLastName());
        assertEquals("johndoe@abc.com", response.getBody().getEmail());
    }

    @Test
    public void updateCustomerTest() {
        // creating new customer
        ResponseEntity<Customer> createResponse = createCustomer();
        String customerId = createResponse.getBody().getId();
        Customer toUpdate = new Customer(
                customerId,
                createResponse.getBody().getFirstName(),
                createResponse.getBody().getLastName(),
                createResponse.getBody().getEmail()
        );

        // altering object values
        toUpdate.setFirstName("Jane");
        toUpdate.setEmail("janedoe@abc.com");
        HttpEntity<Customer> request = new HttpEntity<>(toUpdate);

        /*Map<String, String> param = new HashMap<String, String>();
        param.put("id", toUpdate.getId());
        param.put("firstName", toUpdate.getFirstName());
        param.put("email", toUpdate.getEmail());*/

        // calling update request
        ResponseEntity<Boolean> updateResponse = testRestTemplate.exchange("/api/customers", HttpMethod.PUT, request, Boolean.class);
        assertEquals(true, updateResponse.getBody());

        // fetching customer by id
        ResponseEntity<Customer> getResponse = testRestTemplate.getForEntity("/api/customers/" + customerId, Customer.class);

        assertNotNull(getResponse.getBody().getId());
        assertEquals(toUpdate.getId(), getResponse.getBody().getId());
        assertEquals("Jane", getResponse.getBody().getFirstName());
        assertEquals("Doe", getResponse.getBody().getLastName());
        assertEquals("janedoe@abc.com", getResponse.getBody().getEmail());
    }

}

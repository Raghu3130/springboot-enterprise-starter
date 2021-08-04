package com.accio.spring.starter.controllers;

import com.accio.spring.starter.models.Customer;
import com.accio.spring.starter.responses.StandardResponse;
import com.accio.spring.starter.testhelpers.CustomerHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	private final Customer customer;

	CustomerControllerIntegrationTest() {
		this.customer = CustomerHelper.createMockCustomer();
	}

	private ResponseEntity<StandardResponse> createCustomer() {
		HttpEntity<Customer> request = new HttpEntity<>(customer);
		return testRestTemplate.postForEntity("/api/customers", request, StandardResponse.class);
	}

	private Customer getCustomerFromHashMap(HashMap<String, Object> hashMap) {
		Customer c = new Customer();
		c.setId((String) hashMap.get("id"));
		c.setFirstName((String) hashMap.get("firstName"));
		c.setLastName((String) hashMap.get("lastName"));
		c.setEmail((String) hashMap.get("email"));
		return c;
	}

	@Test
	public void createCustomerTest() {
		HttpEntity<Customer> request = new HttpEntity<>(customer);

		ResponseEntity<StandardResponse> response = testRestTemplate.postForEntity("/api/customers", request,
				StandardResponse.class);

		HashMap<String, Object> hashMap = response.getBody() != null
				? (HashMap<String, Object>) response.getBody().getPayload() : null;

		Customer customer = getCustomerFromHashMap(hashMap);

		assertNotNull(customer.getId());
		assertEquals("John", customer.getFirstName());

		assertEquals("Doe", customer.getLastName());
		assertEquals("johndoe@abc.com", customer.getEmail());
	}

	@Test
	public void getSingleCustomerTest() {
		// creating new customer
		ResponseEntity<StandardResponse> createResponse = createCustomer();
		HashMap<String, Object> hashMap = createResponse.getBody() != null
				? (HashMap<String, Object>) createResponse.getBody().getPayload() : null;
		Customer c = getCustomerFromHashMap(hashMap);

		String customerId = c.getId();
		ResponseEntity<StandardResponse> response = testRestTemplate.getForEntity("/api/customers/" + customerId,
				StandardResponse.class);

		HashMap<String, Object> hashMap1 = response.getBody() != null
				? (HashMap<String, Object>) response.getBody().getPayload() : null;
		Customer fetchedCustomer = getCustomerFromHashMap(hashMap1);

		assertNotNull(fetchedCustomer.getId());
		assertEquals("John", fetchedCustomer.getFirstName());
		assertEquals("Doe", fetchedCustomer.getLastName());
		assertEquals("johndoe@abc.com", fetchedCustomer.getEmail());
	}

	@Test
	public void updateCustomerTest() {
		// creating new customer
		ResponseEntity<StandardResponse> createResponse = createCustomer();
		HashMap<String, Object> hashMap = createResponse.getBody() != null
				? (HashMap<String, Object>) createResponse.getBody().getPayload() : null;
		Customer c = getCustomerFromHashMap(hashMap);
		String customerId = c.getId();
		Customer toUpdate = new Customer(customerId, c.getFirstName(), c.getLastName(), c.getEmail());

		// altering object values
		toUpdate.setFirstName("Jane");
		toUpdate.setEmail("janedoe@abc.com");
		HttpEntity<Customer> request = new HttpEntity<>(toUpdate);

		/*
		 * Map<String, String> param = new HashMap<String, String>(); param.put("id",
		 * toUpdate.getId()); param.put("firstName", toUpdate.getFirstName());
		 * param.put("email", toUpdate.getEmail());
		 */

		// calling update request
		ResponseEntity<StandardResponse> updateResponse = testRestTemplate.exchange("/api/customers", HttpMethod.PUT,
				request, StandardResponse.class);
		Boolean isUpdated = (Boolean) updateResponse.getBody().getPayload();
		assertEquals(true, isUpdated);

		// fetching customer by id
		ResponseEntity<StandardResponse> getResponse = testRestTemplate.getForEntity("/api/customers/" + customerId,
				StandardResponse.class);
		HashMap<String, Object> hashMap1 = getResponse.getBody() != null
				? (HashMap<String, Object>) getResponse.getBody().getPayload() : null;
		Customer fetchedCustomer = getCustomerFromHashMap(hashMap1);

		assertNotNull(fetchedCustomer.getId());
		assertEquals(toUpdate.getId(), fetchedCustomer.getId());
		assertEquals("Jane", fetchedCustomer.getFirstName());
		assertEquals("Doe", fetchedCustomer.getLastName());
		assertEquals("janedoe@abc.com", fetchedCustomer.getEmail());
	}

}

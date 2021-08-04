package com.accio.spring.starter.testhelpers;

import com.accio.spring.starter.models.Customer;

import java.util.UUID;

public class CustomerHelper {

	public static Customer createMockCustomer() {
		// Mock the data return by customerService class
		Customer customer = new Customer();
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customer.setEmail("johndoe@abc.com");
		return customer;
	}

	public static Customer createMockCustomerWithId() {
		Customer customer = CustomerHelper.createMockCustomer();
		customer.setId(UUID.randomUUID().toString());
		return customer;
	}

}

package com.accio.spring.starter.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

    /**
     * Primary key id.
     */
    @Id
    private String id;

    /**
     * String firstName for customer's first name.
     */
    private String firstName;

    /**
     * String lastName for customer's last name.
     */
    private String lastName;

    /**
     * String email for customer's email.
     */
    private String email;

    /**
     * Default constructor.
     */
    public Customer() {

    }

    /**
     * All arguments constructor.
     *
     * @param customerId        Customer id as String
     * @param customerFirstName Customer first name as String
     * @param customerLastName  Customer last name as String
     * @param customerEmail     Customer email as String
     */
    public Customer(
            final String customerId,
            final String customerFirstName,
            final String customerLastName,
            final String customerEmail
    ) {
        this.id = customerId;
        this.firstName = customerFirstName;
        this.lastName = customerLastName;
        this.email = customerEmail;
    }

    /**
     * The method getId return customer id.
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * The method takes String as param to set customer id.
     *
     * @param customerId Customer id as String
     */
    public void setId(final String customerId) {
        this.id = customerId;
    }

    /**
     * The method return customer first name.
     *
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * The method takes String as param to set customer first name.
     *
     * @param customerFirstName Customer first name as String
     */
    public void setFirstName(final String customerFirstName) {
        this.firstName = customerFirstName;
    }

    /**
     * The method  return customer last name.
     *
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * The method takes String as param to set customer last name.
     *
     * @param customerLastName Customer last name as String
     */
    public void setLastName(final String customerLastName) {
        this.lastName = customerLastName;
    }

    /**
     * The method return customer email.
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * The method takes String to set customer email.
     *
     * @param customerEmail Customer email as String
     */
    public void setEmail(final String customerEmail) {
        this.email = customerEmail;
    }

    /**
     * The method returns string of all properties.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Customer{" + "id='" + id + '\''
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", email='" + email + '\'' + '}';
    }

}

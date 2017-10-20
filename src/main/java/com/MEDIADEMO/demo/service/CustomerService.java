package com.MEDIADEMO.demo.service;

import java.util.List;

import com.MEDIADEMO.demo.domain.Customer;

public interface CustomerService {

	List<Customer> getAllCustomersBasedOnUserType(String userType);

	Customer getIndivisualCustomer(String id);

	Customer createCustomer(Customer customer);

	void deleteCustomer(Customer customer);

}

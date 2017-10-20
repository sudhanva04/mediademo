package com.MEDIADEMO.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MEDIADEMO.demo.CustomerException;
import com.MEDIADEMO.demo.domain.Customer;
import com.MEDIADEMO.demo.domain.UserType;

import com.MEDIADEMO.demo.repository.CustomerRepository;
import com.mongodb.MongoException;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomersBasedOnUserType(String userType) {

		int priority = getPriorityBasedOnUserType(userType);
		try {
			List<Customer> allCustomers = customerRepository.findByPriorityGreaterThan(priority);
			if (allCustomers != null && !allCustomers.isEmpty()) {
				return allCustomers;
			} else {
				throw new CustomerException("Customers Not Found");
			}

		} catch (MongoException e) {
			throw new CustomerException(e.getMessage());
		}

	}

	private int getPriorityBasedOnUserType(String userType) {
		Map<UserType, Integer> priorityMap = new HashMap<>();
		priorityMap.put(UserType.SUPERUSER, 1);
		priorityMap.put(UserType.ADMIN, 2);
		priorityMap.put(UserType.USER, 3);
		UserType enumType = Stream.of(UserType.values()).filter(t -> t.toString().equals(userType)).findFirst().get();

		return priorityMap.get(enumType);
	}

	@Override
	public Customer getIndivisualCustomer(String id) {
		try {
			Customer customer = customerRepository.findOne(id);
			if (customer != null) {
				return customer;
			} else {
				throw new CustomerException("Customer Not Found");
			}
		} catch (MongoException e) {
			throw new CustomerException(e.getMessage());
		}

	}

	@Override
	public Customer createCustomer(Customer customer) {
		try {
			return customerRepository.save(customer);
		} catch (Exception e) {
			throw new CustomerException(e.getMessage());
		}

	}

	@Override
	public void deleteCustomer(Customer customer) {
		try {
			customerRepository.delete(customer);
		} catch (Exception e) {
			throw new CustomerException(e.getMessage());
		}
	}

}

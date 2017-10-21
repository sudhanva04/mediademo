package com.MEDIADEMO.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
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

	@Autowired
	GridFsTemplate gridFsTemplate;

	@Autowired
	GridFsOperations gridOperations;

	@Override
	public List<Customer> getAllCustomersBasedOnUserType(String userType) {

		int priority = getPriorityBasedOnUserType(userType);
		try {
			List<Customer> allCustomers = customerRepository.findByPriorityGreaterThan(priority - 1);
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
		System.err.println("usertype is   " + userType);
		Map<UserType, Integer> priorityMap = new HashMap<>();
		priorityMap.put(UserType.SUPERUSER, 1);
		priorityMap.put(UserType.ADMIN, 2);
		priorityMap.put(UserType.USER, 3);
		UserType enumType = Stream.of(UserType.values()).filter(t -> t.toString().equals(userType)).findFirst().get();
		System.err.println("usertype enum is   " + userType);
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
			customer.setPriority(getPriorityBasedOnUserType(customer.getUserType().toString()));
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

	@Override
	public Customer getCustomerBasedOnLogin(String userName, String password) {
		try {
			Customer customer = customerRepository.findByName(userName);

			if (customer != null && customer.getPassword().equals(password))
				return customer;
			else
				throw new CustomerException("customer not found");
		} catch (Exception e) {
			throw new CustomerException("customer not found");
		}

	}

	@Override
	public Boolean updateCustomerVideo(InputStream uploadedInputStream,
			FormDataContentDisposition fileDetail) {
		try {
			System.err.println("inside upload impl");
			// byte[] byteArray = readFully(uploadedInputStream);
			gridOperations.store(uploadedInputStream, fileDetail.getFileName(), fileDetail.getType());
			System.err.println("inside upload return");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private byte[] readFully(InputStream input) throws IOException {

		byte[] buffer = new byte[8192];
		int bytesRead;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		while ((bytesRead = input.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}
		return output.toByteArray();
	}

}

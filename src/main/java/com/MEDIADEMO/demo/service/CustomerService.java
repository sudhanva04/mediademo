package com.MEDIADEMO.demo.service;

import java.io.InputStream;
import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.MEDIADEMO.demo.domain.Customer;

public interface CustomerService {

	List<Customer> getAllCustomersBasedOnUserType(String userType);

	Customer getIndivisualCustomer(String id);

	Customer createCustomer(Customer customer);

	void deleteCustomer(Customer customer);

	Customer getCustomerBasedOnLogin(String userName, String password);

	Boolean updateCustomerVideo(InputStream uploadedInputStream,
			FormDataContentDisposition fileDetail);

}

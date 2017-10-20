package com.MEDIADEMO.demo.endpoint;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.MEDIADEMO.demo.domain.Customer;
import com.MEDIADEMO.demo.service.CustomerService;

@Path("/user")
public class UserEndPoint {
	private static final Logger LOG = LoggerFactory.getLogger(UserEndPoint.class);

	@Autowired
	CustomerService customerService;

	@GET
	@Path("/all")
	public List<Customer> getAllCustomersBasedOnUserType(@QueryParam("usertype") String userType) {
		return customerService.getAllCustomersBasedOnUserType(userType);
	}

	@GET
	public Customer getIndivisualCustomer(@QueryParam("id") String id) {
		return customerService.getIndivisualCustomer(id);
	}

	@POST
	public Customer createCustomer(Customer customer) {
		return customerService.createCustomer(customer);
	}

	@PUT
	public Customer updateCustomer(Customer customer) {
		return customerService.createCustomer(customer);
	}

	@DELETE
	public void deleteCustomer(Customer customer) {
		customerService.deleteCustomer(customer);
	}

}

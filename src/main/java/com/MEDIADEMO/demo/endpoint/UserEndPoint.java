package com.MEDIADEMO.demo.endpoint;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.MEDIADEMO.demo.domain.Customer;
import com.MEDIADEMO.demo.domain.HelloDomain;
import com.MEDIADEMO.demo.service.CustomerService;

@Path("/user")

public class UserEndPoint {
	private static final Logger LOG = LoggerFactory.getLogger(UserEndPoint.class);

	@Autowired
	CustomerService customerService;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON )
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Customer> getAllCustomersBasedOnUserType(@QueryParam("usertype") String userType) {
		System.err.println("get all");
		return customerService.getAllCustomersBasedOnUserType(userType);
	}

	
	@GET
	@Path("/herokutry")
	public Customer getDemoCustomer() {
		Customer cust = new Customer();
		cust.setName("tryman");
		return cust;
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

	@GET
	@Path("/login")
	public Customer getCustomerBasedOnLogin(@QueryParam("userName") String userName,
			@QueryParam("password") String password) {
		System.err.println("inside get method");
		return customerService.getCustomerBasedOnLogin(userName, password);
	}
	
	
	@POST
	@Path("/uploadvideo")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Boolean updateStudentImage(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		System.err.println("inside upload");
		return customerService.updateCustomerVideo(uploadedInputStream, fileDetail);

	}
}

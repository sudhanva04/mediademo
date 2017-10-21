package com.MEDIADEMO.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.MEDIADEMO.demo.domain.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

	List<Customer> findByPriorityGreaterThan(int priority);

	Customer findByName(String name);

}

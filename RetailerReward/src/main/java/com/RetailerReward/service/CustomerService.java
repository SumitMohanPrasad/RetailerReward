package com.RetailerReward.service;

import java.util.List;

import com.RetailerReward.model.Customer;

public interface CustomerService {

	    List<Customer> getAllCustomers();
	    Customer getCustomerById(Long id);
	    Customer addCustomer(Customer customer);
	    void deleteCustomerById(Long id);
}

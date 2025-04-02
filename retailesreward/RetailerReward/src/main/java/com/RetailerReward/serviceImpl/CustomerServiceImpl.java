package com.RetailerReward.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.RetailerReward.Repository.CustomerRepository;
import com.RetailerReward.exception.CustomerNotFoundException;
import com.RetailerReward.model.Customer;
import com.RetailerReward.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private final CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository= customerRepository;
	}
	
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer With Id "+id+" Not Found"));
	}
	
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public void deleteCustomerById(Long id) {
		if(!customerRepository.existsById(id)) {
			throw new CustomerNotFoundException("Customer With Id "+id+" Not Found");
		}
		customerRepository.deleteById(id);
	}

	
}

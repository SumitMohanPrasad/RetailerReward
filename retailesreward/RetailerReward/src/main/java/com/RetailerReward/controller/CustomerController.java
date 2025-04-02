package com.RetailerReward.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RetailerReward.model.Customer;
import com.RetailerReward.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService=customerService;
	}
	
	@GetMapping
	public List<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	
	}
	
	@GetMapping("/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") Long customerId){
		return customerService.getCustomerById(customerId);
	}
	
	@PostMapping
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}
	
	@DeleteMapping("/{customerId}")
	public String deleteCustomer(@PathVariable Long customerId) {
		customerService.deleteCustomerById(customerId);
		return "Customer deleted Successfully";
	}
	
}

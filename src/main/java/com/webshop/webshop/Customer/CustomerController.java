package com.webshop.webshop.Customer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Resource 
	CustomerDaoImpl customerService;

	@GetMapping(value="/{id}")
	public Customer getCustomer(@PathVariable("id") int customerId){
		return customerService.findCustomerById(customerId);
	}

	@GetMapping(value = "/all")
	public List<Customer> getCustomers() {
		return customerService.findAll();

	}
	
	@PostMapping(value="/")
	public void createCustomer(@RequestBody Customer customer) {
		customerService.createCustomer(customer);
	}
}

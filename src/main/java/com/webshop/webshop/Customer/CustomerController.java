package com.webshop.webshop.Customer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Resource 
	CustomerDaoImpl customerService;

	@GetMapping(value="/{id}")
	public Customer GetCustomer(@PathVariable("id") int customerId){
		return customerService.FindCustomerById(customerId);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/all")
	public List<Customer> GetAllCustomers() {
		return customerService.FindAll();
	}


	@PostMapping()
	public Customer CreateCustomer(@RequestBody Customer customer) {
		return customerService.CreateCustomer(customer);
	}

	@PutMapping(value="/{id}")
	public void UpdateCustomer(@PathVariable("id") int customerId, @RequestBody Customer customer){
		customerService.UpdateCustomer(customer);
	}

	@DeleteMapping(value="/{id}")
	public void DeleteCustomer(@PathVariable("id") int customerId){
		customerService.DeleteCustomer(customerId);
	}
}

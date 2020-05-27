package com.webshop.webshop.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

	Customer FindCustomerById(int customerId);

	Optional<Customer> FindCustomerByEmail(String email);

	List<Customer> FindAll();

	Customer CreateCustomer(Customer customer);

	void UpdateCustomer(Customer customer);

	void DeleteCustomer(int customerId);
}

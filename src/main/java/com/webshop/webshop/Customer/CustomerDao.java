package com.webshop.webshop.Customer;

import java.util.List;

public interface CustomerDao {

	Customer FindCustomerById(int customerId);

	List<Customer> FindAll();

	Customer CreateCustomer(Customer customer);

	void UpdateCustomer(Customer customer);

	void DeleteCustomer(int customerId);
}

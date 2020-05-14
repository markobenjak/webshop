package com.webshop.webshop.Customer;

import java.util.List;

public interface CustomerDao {

	Customer findCustomerById(int customerId);

	List<Customer> findAll();

	void createCustomer(Customer customer);

}

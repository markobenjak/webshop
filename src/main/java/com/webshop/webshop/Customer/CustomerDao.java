package com.webshop.webshop.Customer;

import java.util.List;

public interface CustomerDao {

	List<Customer> findAll();

	void createCustomer(Customer customer);

}

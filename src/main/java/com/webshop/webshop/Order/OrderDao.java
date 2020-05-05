package com.webshop.webshop.Order;

import java.util.List;

public interface OrderDao {

	List<Order> findAll();

	void createOrder(Order order);
	List<Order> readOrder(Order order);

	void deleteOrder(Order order);

	void finalizeOrder(Order order);
}

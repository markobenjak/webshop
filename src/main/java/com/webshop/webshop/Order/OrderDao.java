package com.webshop.webshop.Order;

import java.util.List;

public interface OrderDao {

	List<Order> findAll();

	void createOrder(Order order);
	
	void deleteOrder(int orderId);

	void finalizeOrder(Order order);

	List<EntireOrder> readOrder(Integer cusotmerId);
}

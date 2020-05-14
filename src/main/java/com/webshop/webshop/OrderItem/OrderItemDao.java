package com.webshop.webshop.OrderItem;

import java.util.List;

public interface OrderItemDao {

	List<OrderItem> findAll();

	void updateOrder(OrderItem orderItem);

	void deleteOrderItem(OrderItem orderItem);
}

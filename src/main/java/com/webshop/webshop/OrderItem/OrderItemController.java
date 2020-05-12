package com.webshop.webshop.OrderItem;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.webshop.webshop.Order.Order;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {
	
	@Resource 
	OrderItemDaoImpl orderItemService;

	@GetMapping(value = "/list")
	public List<OrderItem> getOrderItemList() {
		return orderItemService.findAll();

	}
	
	@PutMapping(value = "/")
	public void createOrder(@RequestBody OrderItem orderItem) {
		orderItemService.updateOrder(orderItem);
	}
	
	
}

package com.webshop.webshop.OrderItem;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webshop")
public class OrderItemController {
	
	@Resource 
	OrderItemDaoImpl orderItemService;

	@GetMapping(value = "/list")
	public List<OrderItem> getOrderItemList() {
		return orderItemService.findAll();

	}
	
	@PostMapping(value = "/")
	public void createOrder(@RequestBody OrderItem orderItem) {
		orderItemService.updateOrder(orderItem);
	}
	
	@DeleteMapping(value = "/deleteOrderItemById")
	public void deleteOrderItem(@RequestBody OrderItem orderItem) {
		orderItemService.deleteOrderItem(orderItem);	
	}
	
	
}

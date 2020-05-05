package com.webshop.webshop.OrderItem;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.webshop.Order.Order;

@RestController
@RequestMapping("/webshop")
public class OrderItemController {
	
	@Resource 
	OrderItemDaoImpl orderItemService;

	@GetMapping(value = "/orderItemList")
	public List<OrderItem> getOrderItemList() {
		return orderItemService.findAll();

	}
	
	@PostMapping(value = "/updateOrder")
	public void createOrder(@RequestBody OrderItem orderItem) {
		orderItemService.updateOrder(orderItem);
	}
	
	
}

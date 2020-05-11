package com.webshop.webshop.Order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webshop")
public class OrderController {
	
	@Resource 
	OrderDaoImpl orderService;

	@GetMapping(value = "/order/list")
	public List<Order> getOrders() {
		return orderService.findAll();

	}

	@PostMapping(value = "/order")
	public void createOrder(@RequestBody Order order) {
		orderService.createOrder(order);
	}
	
	@GetMapping(value = "/order")
	public List<Order> readOrder(@RequestBody Order order) {
		return orderService.readOrder(order);

	}
	
	@DeleteMapping(value = "/order/delete/id")
	public void deleteOrder(@RequestParam("id") Integer orderId) {
		orderService.deleteOrder(orderId);
	}
	
	@PostMapping(value = "/finalizeOrder")
	public void finalizeOrder(@RequestBody Order order) {
		orderService.finalizeOrder(order);
	}
	
}

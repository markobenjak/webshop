package com.webshop.webshop.Order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Resource 
	OrderDaoImpl orderService;

	@GetMapping(value = "/list")
	public List<Order> getOrders() {
		return orderService.findAll();

	}

	@PostMapping(value = "/")
	public void createOrder(@RequestBody Order order) {
		orderService.createOrder(order);
	}
	
	@GetMapping(value = "/")
	public List<Order> readOrder(@RequestBody Order order) {
		return orderService.readOrder(order);

	}
	
	@DeleteMapping(value = "/delete/id")
	public void deleteOrder(@RequestParam("id") Integer orderId) {
		orderService.deleteOrder(orderId);
	}
	
	@PostMapping(value = "/finalize")
	public void finalizeOrder(@RequestBody Order order) {
		orderService.finalizeOrder(order);
	}
	
}

package com.webshop.webshop.Order;

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
public class OrderController {
	
	@Resource 
	OrderDaoImpl orderService;

	@GetMapping(value = "/orderList")
	public List<Order> getOrders() {
		return orderService.findAll();

	}

	@PostMapping(value = "/createOrder")
	public void createOrder(@RequestBody Order order) {
		orderService.createOrder(order);
	}
	
	@PostMapping(value = "/readOrder")
	public List<Order> readOrder(@RequestBody Order order) {
		return orderService.readOrder(order);

	}
	
	@DeleteMapping(value = "/deleteOrderById")
	public void deleteOrder(@RequestBody Order order) {
		orderService.deleteOrder(order);	
	}
	
	@PostMapping(value = "/finalizeOrder")
	public void finalizeOrder(@RequestBody Order order) {
		orderService.finalizeOrder(order);
	}
	
}

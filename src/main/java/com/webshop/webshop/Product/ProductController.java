package com.webshop.webshop.Product;

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
public class ProductController {
	
	@Resource 
	ProductDaoImpl productService;

	@GetMapping(value = "/productList")
	public List<Product> getProducts() {
		return productService.findAll();

	}
	
	@PostMapping(value = "/validateProduct")
	public String validate(@RequestBody Product product) {
		return productService.validateProduct(product);

	}
}

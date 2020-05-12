package com.webshop.webshop.Product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.webshop.webshop.Order.Order;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Resource 
	ProductDaoImpl productService;

	@GetMapping(value = "/list")
	public List<Product> getProducts() {
		return productService.findAll();

	}

	//@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/validate/id")
	public String validate(@RequestParam("id") int productId) {
		return productService.validateProduct(productId);

	}
}

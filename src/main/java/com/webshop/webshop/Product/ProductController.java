package com.webshop.webshop.Product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.webshop.webshop.Order.Order;

@RestController
@RequestMapping("/webshop")
public class ProductController {
	
	@Resource 
	ProductDaoImpl productService;

	@GetMapping(value = "/product/list")
	public List<Product> getProducts() {
		return productService.findAll();

	}
	
	@GetMapping(value = "/product/validate/id")
	public String validate(@RequestParam("id") int productId) {
		return productService.validateProduct(productId);

	}
}

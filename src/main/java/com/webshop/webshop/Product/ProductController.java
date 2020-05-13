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

	@GetMapping(value = "/{id}")
	public Product getProduct(@PathVariable("id") int productId){
		return productService.singleProduct(productId);
	}

	@GetMapping(value = "/list")
	public List<Product> getProducts() {
		return productService.findAll();

	}

	@GetMapping(value = "/validate/{id}")
	public String validate(@PathVariable("id") int productId) {
		return productService.validateProduct(productId);

	}
}

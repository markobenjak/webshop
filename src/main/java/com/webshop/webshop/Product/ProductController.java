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

	@PostMapping(value = "/create/")
	public void createProduct(@RequestBody Product product) {
		productService.createProduct(product);
	}

	@PutMapping(value = "/update/")
	public void updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
	}

	@GetMapping(value = "/validate/{id}")
	public String validate(@PathVariable("id") int productId) {
		return productService.validateProduct(productId);
	}

	@DeleteMapping(value = "/delete/{id}")
	public void deleteProduct(@PathVariable("id") Integer productId) {
		productService.deleteProduct(productId);
	}

	//GET: /api/product/search?product=nekiproizvod
	@GetMapping(value = "/search")
	public List<Product> searchProducts(@RequestParam("product") String searchString) {
		return productService.searchProduct(searchString);
	}
}

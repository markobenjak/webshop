package com.webshop.webshop.Product;

import java.util.List;

public interface ProductDao {

	Product singleProduct(int productId);

	List<Product> findAll();

	void createProduct(Product product);

	void updateProduct(Product product);

	String validateProduct(int productId);

	void deleteProduct(int productId);

	List<Product> searchProduct(String searchString);
}

package com.webshop.webshop.Product;

import java.util.List;

public interface ProductDao {

	List<Product> findAll();

	String validateProduct(Product product);
}

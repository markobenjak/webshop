package com.webshop.webshop.Product;

import java.util.List;

public interface ProductDao {

	Product singleProduct(int productId);

	List<Product> findAll();

	String validateProduct(int productId);
}

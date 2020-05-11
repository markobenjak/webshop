package com.webshop.webshop.Product;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.webshop.webshop.OrderItem.OrderItem;
import com.webshop.webshop.OrderItem.OrderItemRowMapper;


@Service
public class ProductDaoImpl implements ProductDao {

	
	public ProductDaoImpl(NamedParameterJdbcTemplate template) {  

        this.template = template;  

	}  

	NamedParameterJdbcTemplate template;  
	
	@Override
	public List<Product> findAll() {
		return template.query("select * from shop.product", new ProductRowMapper());		
	}
	
	@Override
	public String validateProduct(int productId) {
		
		final String productSql = "select * from shop.product where id = :id";
		SqlParameterSource productParam = new MapSqlParameterSource()
				.addValue("id", productId);
		
		ProductRowMapper productMaper = new ProductRowMapper();
		List<Product> itemList = template.query(productSql, productParam ,productMaper);
		
		if(itemList.get(0).is_available()) {
			return "Product " + itemList.get(0).getName() + " is available";
		}else {
			return "Product " + itemList.get(0).getName() + " is not available";
		}
	}


}

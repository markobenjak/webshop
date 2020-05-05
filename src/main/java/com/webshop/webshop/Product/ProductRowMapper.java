package com.webshop.webshop.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductRowMapper implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int arg1) throws SQLException {
		Product product = new Product();
		
		product.setId(rs.getInt("id"));
		product.setCode(rs.getString("code"));
		product.setDescription(rs.getString("description"));
		product.setName(rs.getString("name"));
		product.setPrice_hrk(rs.getInt("price_hrk"));
		product.setIs_available(rs.getBoolean("is_available"));
		
		return product;

	}

}

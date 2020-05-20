package com.webshop.webshop.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EntireOrderRowMapper implements RowMapper<EntireOrder>{

	@Override
	public EntireOrder mapRow(ResultSet rs, int arg1) throws SQLException {
		EntireOrder entireOrder = new EntireOrder();
		
		entireOrder.setId(rs.getInt("id"));
		entireOrder.setPriceHrk(rs.getInt("price_hrk"));
		entireOrder.setDescription(rs.getString("description"));
		entireOrder.setProductName(rs.getString("name"));
		entireOrder.setQuantity(rs.getInt("quantity"));
		
		return entireOrder;

	}

}
